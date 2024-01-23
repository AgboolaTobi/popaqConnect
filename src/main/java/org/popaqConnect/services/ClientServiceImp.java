package org.popaqConnect.services;

import lombok.extern.slf4j.Slf4j;
import org.popaqConnect.data.models.Book;
import org.popaqConnect.data.models.Client;
import org.popaqConnect.data.models.ServiceProvider;
import org.popaqConnect.data.repositories.ClientRepository;

import org.popaqConnect.dtos.requests.RegisterRequest;

import org.popaqConnect.dtos.requests.*;
import org.popaqConnect.dtos.response.BookResponse;

import org.popaqConnect.exceptions.InvalidDetailsException;
import org.popaqConnect.exceptions.InvalidLoginException;
import org.popaqConnect.exceptions.UnAvailableException;
import org.popaqConnect.exceptions.UserExistException;
import org.popaqConnect.utils.Mapper;
import org.popaqConnect.utils.VerifyPassword;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ClientServiceImp implements ClientService{
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    ServiceProviderServices servicePriovider;
    @Autowired
    BookServices bookServices;
    @Autowired
    AdminService adminService;

    @Override
    public void register(RegisterRequest registerRequest) {
        if(userExist(registerRequest.getEmail()))throw new UserExistException("User exist");
        if(!VerifyPassword.verifyPassword(registerRequest.getPassword()))throw new InvalidDetailsException("Wrong password format");
        if(!VerifyPassword.verifyEmail(registerRequest.getEmail()))throw new InvalidDetailsException("Invalid email format");
        Client newClient = Mapper.mapClient(registerRequest);
        clientRepository.save(newClient);
    }

    @Override
    public void login(LoginRequest loginRequest) {
        Client client = clientRepository.findByEmail(loginRequest.clientEmail);
        if (client == null) throw new InvalidLoginException("Invalid login details");
        verifyLoginPassword(loginRequest.getPassword(),loginRequest.getClientEmail());
        client.setLoginStatus(true);

        clientRepository.save(client);
    }

    @Override
    public BookResponse bookServices(BookRequest bookRequest) {
        Client client = clientRepository.findByEmail(bookRequest.getClientEmail());
        if(client == null)throw new UserExistException("User doesn't exist");
        Optional<ServiceProvider> servicePriovider1 = servicePriovider.findUser(bookRequest.getServiceProviderEmail());
        if(servicePriovider1.isEmpty())throw new UserExistException("User doesn't exist");
        if(!servicePriovider1.get().isAvailable())throw new UnAvailableException("User is not available");
        String bookingId = bookServices.save(bookRequest);
        BookResponse bookResponse = new BookResponse();
        adminService.sendClientBookingRequestEmail(bookRequest,bookingId);
        bookResponse.setMessage("your booking id is " + bookingId);
        return  bookResponse;

    }

    @Override
    public List<Book> findAllBookingRequest(String mail) {
        Client client = clientRepository.findByEmail(mail);
        if(!userExist(mail))throw new UserExistException("User doesn't Exist");
        return bookServices.findUserBookRequest(client.getEmail());
    }

    @Override
    public Book findABookRequest(FindABookRequest findABookRequest) {
        Client client = clientRepository.findByEmail(findABookRequest.getEmail());
        if(!userExist(findABookRequest.getEmail()))throw new UserExistException("user doesn't exist");
        Book booking = bookServices.findABookRequest(findABookRequest.getBookId(), findABookRequest.getEmail());
        return booking;
    }


    private boolean userExist(String email){
        Client client = clientRepository.findByEmail(email);
        return client!=null;
    }
    private void verifyLoginPassword(String password,String email){
        Client client = clientRepository.findByEmail(email);
        if(!client.getPassword().equals(password))throw new InvalidLoginException("Invalid login details");

    }


}
