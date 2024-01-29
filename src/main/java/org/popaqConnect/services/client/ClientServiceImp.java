package org.popaqConnect.services.client;


import org.popaqConnect.data.models.Client;
import org.popaqConnect.data.models.Job;
import org.popaqConnect.data.repositories.ClientRepository;
import org.popaqConnect.dtos.requests.RegisterRequest;
import org.popaqConnect.dtos.requests.SearchByDRopTitleRequest;

import org.popaqConnect.data.models.Book;
import org.popaqConnect.data.models.ServiceProvider;

import org.popaqConnect.dtos.requests.*;
import org.popaqConnect.dtos.response.BookResponse;

import org.popaqConnect.exceptions.*;
import org.popaqConnect.services.Admin.AdminService;
import org.popaqConnect.services.Booking.BookServices;
import org.popaqConnect.services.job.JobService;
import org.popaqConnect.services.serviceProvider.ServiceProviderServices;
import org.popaqConnect.utils.Mapper;
import org.popaqConnect.utils.Verification;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;

import java.util.Optional;


@Service
public class ClientServiceImp implements ClientService {
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    JobService jobService;
    @Autowired
    ServiceProviderServices serviceProvider;
    @Autowired
    BookServices bookServices;
    @Autowired
    AdminService adminService;


    @Override
    public void register(RegisterRequest registerRequest) {
        if(userExist(registerRequest.getEmail()))throw new UserExistException("User exist");
        if(!Verification.verifyPassword(registerRequest.getPassword()))throw new InvalidDetailsException("Wrong password format");
        if(!Verification.verifyEmail(registerRequest.getEmail()))throw new InvalidDetailsException("Invalid email format");
        Client newClient = Mapper.mapClient(registerRequest);
        clientRepository.save(newClient);
    }
    @Override
    public List<Job> searchBYDropTitle(SearchByDRopTitleRequest search) {
        Client client = clientRepository.findByEmail(search.getEmail());
        List<Job> jobList = jobService.findByTitle(search.getTitle());
        clientRepository.save(client);
        return jobList;
    }


    @Override
    public void login(ClientLoginRequest loginRequest) {
        Client client = clientRepository.findByEmail(loginRequest.getEmail());
        if (client == null) throw new InvalidLoginException("Invalid login details");
        verifyLoginPassword(loginRequest.getPassword(),loginRequest.getEmail());
        client.setLoginStatus(true);
        clientRepository.save(client);
     }

    @Override
    public BookResponse bookServices(BookRequest bookRequest) {
        Client client = clientRepository.findByEmail(bookRequest.getClientEmail());
        if(client == null)throw new UserExistException("User doesn't exist");
        if(!userExist(client.getEmail()))throw new UserExistException("User doesn't Exist");
        Optional<ServiceProvider> serviceProvider1 = serviceProvider.findUser(bookRequest.getServiceProviderEmail());
        if(serviceProvider1.isEmpty())throw new UserExistException("User doesn't exist");
        if(!serviceProvider1.get().isAvailable())throw new UnAvailableException("User is not available");
        String bookingId = bookServices.save(bookRequest);
        BookResponse bookResponse = new BookResponse();
        adminService.sendClientBookingRequestEmail(servicePriovider1.get().getFirstName(),bookRequest,bookingId);
        bookResponse.setMessage(bookingId);
        return  bookResponse;

    }

    @Override
    public List<Book> viewAllBookingHistory(String mail) {
        Client client = clientRepository.findByEmail(mail);
        if(!userExist(mail))throw new UserExistException("User doesn't Exist");
        if(!isLocked(mail))throw new AppLockedException("Kindly login");
        return bookServices.findAllBookingRequest(client.getEmail());
    }

    @Override
    public Book viewABookingHistory(FindABookRequest findABookRequest) {
        if(!userExist(findABookRequest.getEmail()))throw new UserExistException("user doesn't exist");
        if(!isLocked(findABookRequest.getEmail()))throw new AppLockedException("Kindly login");
        Book booking = bookServices.findABookingRequest(findABookRequest.getBookId(), findABookRequest.getEmail());
        if(booking == null)throw new BookingRequestException("Booking request is invalid");
        return booking;
    }
    private boolean userExist(String email){
        Client client = clientRepository.findByEmail(email);
        return client!=null;
    }
    private boolean isLocked(String email){
        Client client = clientRepository.findByEmail(email);
        if(client.isLoginStatus())return true;
        return false;
    }
    private void verifyLoginPassword(String password,String email){
        Client client = clientRepository.findByEmail(email);
        if(!client.getPassword().equals(password))throw new InvalidLoginException("Invalid login details");

    }


}
