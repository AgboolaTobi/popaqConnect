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

import static org.popaqConnect.utils.Verification.*;


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
        if(!verifyPassword(registerRequest.getPassword()))throw new InvalidDetailsException("Wrong password format");
        if(!verifyEmail(registerRequest.getEmail()))throw new InvalidDetailsException("Invalid email format");
        if (!verifyPhoneNumber(registerRequest.getPhoneNumber())) throw new InvalidDetailsException("Invalid PhoneNumber format");
        Client newClient = Mapper.mapClient(registerRequest);
        clientRepository.save(newClient);
    }
    @Override
    public List<ServiceProvider> searchBYDropTitle(SearchByDRopTitleRequest search) {
        Client client = clientRepository.findByEmail(search.getEmail());
        if (client == null )throw new UserDoesNotExistException("User Doesnt Exist ");
        boolean status = client.isLoginStatus();
        if (!status){
            throw new AppLockedException(search.getEmail()+" Not Active");
        }
        List<ServiceProvider> serviceProviders = serviceProvider.findByTitle(search.getTitle());
        if(serviceProviders.isEmpty())throw new UserExistException("User Doesnt Exist");
        return serviceProviders;
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
    public BookResponse  bookServices(BookRequest bookRequest) {
        Client client = clientRepository.findByEmail(bookRequest.getClientEmail());
        if(client == null)throw new UserExistException("User doesn't exist");
        if(!userExist(client.getEmail()))throw new UserExistException("User doesn't Exist");
        if(!client.isLoginStatus()) throw new AppLockedException("Kindly login");
        Optional<ServiceProvider> serviceProvider1 = serviceProvider.findUser(bookRequest.getServiceProviderEmail());
        if(serviceProvider1.isEmpty())throw new UserExistException("User doesn't exist");
        if(!serviceProvider1.get().isAvailable())throw new UnAvailableException("User is not available");
        String bookingId = bookServices.save(bookRequest);
        BookResponse bookResponse = new BookResponse();
        adminService.sendClientBookingRequestEmail(serviceProvider1.get().getUserName(),bookRequest,bookingId);
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

    @Override
    public List<ServiceProvider> searchByCategory(SearchByCategory searchByCategory) {
        Client client = clientRepository.findByEmail(searchByCategory.getEmail());
        if (client == null) throw new UserDoesNotExistException("User Doesnt Exist ");
        boolean status = client.isLoginStatus();
        if (!status){
            throw new AppLockedException(searchByCategory.getEmail()+" Not Active");
        }
        List<ServiceProvider> serviceProviders = serviceProvider.searchByCategory(searchByCategory.getCategory());
        if(serviceProviders.isEmpty())throw new UserExistException("User Doesnt Exist");
        return serviceProviders;
    }


    @Override
    public void cancelBookingRequest(ClientCancelBookingRequest cancelRequest) {
        if(!userExist(cancelRequest.getClientEmail()))throw new UserExistException("user doesn't exist");
        if(!isLocked(cancelRequest.getClientEmail()))throw new AppLockedException("Kindly login");
        bookServices.cancelBookRequest(cancelRequest.getBookingId(), cancelRequest.getClientEmail());
        serviceProvider.save(cancelRequest.getServiceProviderEmail());
        adminService.sendCancelEmail(cancelRequest.getServiceProviderEmail(),cancelRequest.getBookingId());
    }


    @Override
    public void update(ClientUpdateRequest clientUpdateRequest) {
       Client existingClient = clientRepository.findByEmail(clientUpdateRequest.getEmail());
       if (existingClient==null) throw new UserExistException("User does not exist");
       if (!existingClient.getPassword().equals(clientUpdateRequest.getPassword())) throw new InvalidDetailsException("Invalid user details");
      if(clientUpdateRequest.getEmail() != null) existingClient.setEmail(clientUpdateRequest.getEmail());
      if(clientUpdateRequest.getUserName() != null) existingClient.setUserName(clientUpdateRequest.getUserName());
      if(clientUpdateRequest.getPassword() != null)existingClient.setPassword(clientUpdateRequest.getPassword());
      if(clientUpdateRequest.getAddress()!= null)existingClient.setAddress(clientUpdateRequest.getAddress());
      if(clientUpdateRequest.getPhoneNumber() != null)existingClient.setPhoneNumber(clientUpdateRequest.getPhoneNumber());
      clientRepository.save(existingClient);

    }

    @Override
    public void logout(ClientLogoutRequest clientLogoutRequest) {
        Client existingClient = clientRepository.findByEmail(clientLogoutRequest.getEmail());
        if (existingClient==null) throw new UserExistException("User does not exist");
        existingClient.setLoginStatus(false);
        clientRepository.save(existingClient);
    }

    @Override
    public void deleteAccount(ClientDeleteRequest clientDeleteRequest) {
        Client existingClient = clientRepository.findByEmail(clientDeleteRequest.getEmail());
        if (existingClient==null) throw new UserExistException("User does not exist");
        verifyLogoutPassword(clientDeleteRequest.getPassword(),clientDeleteRequest.getEmail());
        clientRepository.delete(existingClient);
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

    private void verifyLogoutPassword(String password,String email){
        Client client = clientRepository.findByEmail(email);
        if(!client.getPassword().equals(password))throw new InvalidLoginException("Invalid logout details");
    }


}
