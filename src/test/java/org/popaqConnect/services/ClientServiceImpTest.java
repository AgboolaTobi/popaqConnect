package org.popaqConnect.services;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.popaqConnect.data.repositories.ClientRepository;
import org.popaqConnect.dtos.requests.RegisterRequest;
import org.popaqConnect.exceptions.InvalidDetailsException;
import org.popaqConnect.data.models.Book;
import org.popaqConnect.data.BookType;
import org.popaqConnect.data.repositories.BookRepository;
import org.popaqConnect.data.repositories.ClientRepository;
import org.popaqConnect.data.repositories.ServiceProviderRepository;
import org.popaqConnect.dtos.requests.*;
import org.popaqConnect.dtos.response.BookResponse;
import org.popaqConnect.exceptions.InvalidDetailsException;
import org.popaqConnect.exceptions.InvalidLoginException;
import org.popaqConnect.exceptions.UserExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ClientServiceImpTest {
    @Autowired
    ClientService clientService;
    @Autowired
    ClientRepository clientRepository;

    @Autowired
    ServiceProviderRepository  serviceProviderRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    ServiceProviderServices serviceProviderServices;
    @Autowired
    BookServices bookServices;
    @AfterEach
    public void doThisAfterEach(){
        clientRepository.deleteAll();
        serviceProviderRepository.deleteAll();
         bookRepository.deleteAll();
    }
    @Test
    public void testThatIfClientRegistersWithInvalidPasswordFormatThrowsAndException(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUserName("ope");
        registerRequest.setPassword("1234");
        registerRequest.setEmail("uoidhshdgtytdwgy");
        registerRequest.setAddress("yaba mowe");
        registerRequest.setPhoneNumber("66t77253827673");
        assertThrows(InvalidDetailsException.class,()-> clientService.register(registerRequest));
    }
    @Test
    public void testThatIfClientRegistersWithInvalidEmailFormatThrowsAndException(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUserName("ope");
        registerRequest.setPassword("Opedert13@");
        registerRequest.setEmail("ope@");
        registerRequest.setAddress("yaba mowe");
        registerRequest.setPhoneNumber("66t77253827673");
        assertThrows(InvalidDetailsException.class,()-> clientService.register(registerRequest));
    }
    @Test
    public void testThatIfClientRegistersWithInvalidPhoneNumberFormatThrowsAndException(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUserName("ope");
        registerRequest.setPassword("Opedert13@");
        registerRequest.setEmail("ope@gmail.com");
        registerRequest.setAddress("yaba mowe");
        registerRequest.setPhoneNumber("66t77253827673");
        clientService.register(registerRequest);
        assertThrows(UserExistException.class,()-> clientService.register(registerRequest));
    }
    @Test
    public void testThatIfClientTriesToLoginWithWrongEmailAddressThrowsException(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUserName("ope");
        registerRequest.setPassword("Opedert13@");
        registerRequest.setEmail("ope@gmail.com");
        registerRequest.setAddress("yaba mowe");
        registerRequest.setPhoneNumber("66t77253827673");
        clientService.register(registerRequest);

        ClientLoginRequest loginRequest = new ClientLoginRequest();
        loginRequest.setEmail("ope@gmail.com");
        loginRequest.setPassword("Opedert");
        assertThrows(InvalidLoginException.class,()->clientService.login(loginRequest));
    }
    @Test
    public void testThatUserCantBookAServiceTheServiceOfAServiceProviderThatIsNotAvailable(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUserName("ope");
        registerRequest.setPassword("Ope13@");
        registerRequest.setEmail("ope@gmail.com");
        registerRequest.setAddress("yaba mowe");
        registerRequest.setPhoneNumber("66t77253827673");
        clientService.register(registerRequest);

        ClientLoginRequest loginRequest = new ClientLoginRequest();
        loginRequest.setEmail("ope@gmail.com");
        loginRequest.setPassword("Ope13@");
        clientService.login(loginRequest);

        ServiceProviderRegisterRequest registerRequests = new ServiceProviderRegisterRequest();
        registerRequests.setUserName("ope");
        registerRequests.setPassword("PhilipOdey@75");
        registerRequests.setEmail("opeoluwaagnes@gmail.com");
        registerRequests.setAddress("yaba mowe");
        registerRequests.setPhoneNumber("+2349019539651");
        registerRequests.setYearsOfExperience(2);
        registerRequests.setBioData("i an philip i am a software engineer");
        registerRequests.setChargePerHour(2500.00);
        registerRequests.setCategory("ENGINEER");
        registerRequests.setJobTitle("Software engineer");

        serviceProviderServices.register(registerRequests);

        LoginRequest loginRequests = new LoginRequest();
        loginRequests.setEmail("opeoluwaagnes@gmail.com");
        loginRequests.setPassword("PhilipOdey@75");
        serviceProviderServices.login(loginRequests);


        BookRequest bookRequest = new BookRequest();
        bookRequest.setServiceProviderEmail("opeoluwaagnes@gmail.com");
        bookRequest.setDescription("your service as hairstylist");
        bookRequest.setTime("2 hours");
        bookRequest.setClientEmail("ope@gmail.com");
        clientService.bookServices(bookRequest);
        List<Book> clientsBooks = clientService.findAllBookingRequest("ope@gmail.com");
        assertEquals(1,clientsBooks.size());

    }
    @Test
    public void testThatWhenAClientBookARequestAndServiceProviderIsAvailabilityBecomesFalse(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUserName("ope");
        registerRequest.setPassword("Ope13@");
        registerRequest.setEmail("ope@gmail.com");
        registerRequest.setAddress("yaba mowe");
        registerRequest.setPhoneNumber("66t77253827673");
        clientService.register(registerRequest);

        ClientLoginRequest loginRequest = new ClientLoginRequest();
        loginRequest.setEmail("ope@gmail.com");
        loginRequest.setPassword("Ope13@");
        clientService.login(loginRequest);

        ServiceProviderRegisterRequest registerRequests = new ServiceProviderRegisterRequest();
        registerRequests.setUserName("ope");
        registerRequests.setPassword("PhilipOdey@75");
        registerRequests.setEmail("opeoluwaagnes@gmail.com");
        registerRequests.setAddress("yaba mowe");
        registerRequests.setPhoneNumber("+2349019539651");
        registerRequests.setYearsOfExperience(2);
        registerRequests.setBioData("i an philip i am a software engineer");
        registerRequests.setChargePerHour(2500.00);
        registerRequests.setCategory("ENGINEER");
        registerRequests.setJobTitle("Software engineer");

        serviceProviderServices.register(registerRequests);

        LoginRequest loginRequests = new LoginRequest();
        loginRequests.setEmail("opeoluwaagnes@gmail.com");
        loginRequests.setPassword("PhilipOdey@75");
        serviceProviderServices.login(loginRequests);

        BookRequest bookRequest = new BookRequest();
        bookRequest.setServiceProviderEmail("opeoluwaagnes@gmail.com");
        bookRequest.setDescription("your service as hairstylist");
        bookRequest.setTime("2 hours");
        bookRequest.setClientEmail("ope@gmail.com");
        BookResponse bookingId = clientService.bookServices(bookRequest);
        FindABookRequest findABookRequest = new FindABookRequest();
        findABookRequest.setEmail("ope@gmail.com");
        findABookRequest.setBookId(bookingId.getMessage());
        Book book = clientService.findABookRequest(findABookRequest);
        assertSame(BookType.NOTACCEPTED,book.getProjectStatus());

        AcceptBookingRequest acceptBookingRequest = new AcceptBookingRequest();
        acceptBookingRequest.setId(bookingId.getMessage());
        acceptBookingRequest.setEmail("opeoluwaagnes@gmail.com");
        acceptBookingRequest.setResponse("accepted");
        serviceProviderServices.acceptClientBookRequest(acceptBookingRequest);

        book = clientService.findABookRequest(findABookRequest);
        assertSame(BookType.ACCEPTED,book.getProjectStatus());

    }


}