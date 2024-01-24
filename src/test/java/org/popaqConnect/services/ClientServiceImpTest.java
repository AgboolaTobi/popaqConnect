package org.popaqConnect.services;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.popaqConnect.data.repositories.ClientRepository;
import org.popaqConnect.dtos.requests.RegisterRequest;
import org.popaqConnect.exceptions.InvalidDetailsException;
import org.popaqConnect.data.models.Book;
import org.popaqConnect.data.models.BookType;
import org.popaqConnect.data.repositories.BookRepository;
import org.popaqConnect.data.repositories.ClientRepository;
import org.popaqConnect.data.repositories.ServiceProviderRepository;
import org.popaqConnect.dtos.requests.*;
import org.popaqConnect.exceptions.InvalidDetailsException;
import org.popaqConnect.exceptions.InvalidLoginException;
import org.popaqConnect.exceptions.UnAvailableException;
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
        registerRequest.setFirstName("ope");
        registerRequest.setLastName("Mr Tobi");
        registerRequest.setPassword("1234");
        registerRequest.setEmail("uoidhshdgtytdwgy");
        registerRequest.setAddress("yaba mowe");
        registerRequest.setPhoneNumber("66t77253827673");
        assertThrows(InvalidDetailsException.class,()-> clientService.register(registerRequest));
    }
    @Test
    public void testThatIfClientRegistersWithInvalidEmailFormatThrowsAndException(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setFirstName("ope");
        registerRequest.setLastName("Mr Tobi");
        registerRequest.setPassword("Opedert13@");
        registerRequest.setEmail("ope@");
        registerRequest.setAddress("yaba mowe");
        registerRequest.setPhoneNumber("66t77253827673");
        assertThrows(InvalidDetailsException.class,()-> clientService.register(registerRequest));
    }
    @Test
    public void testThatIfClientRegistersWithInvalidPhoneNumberFormatThrowsAndException(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setFirstName("ope");
        registerRequest.setLastName("Mr Tobi");
        registerRequest.setPassword("Opedert13@");
        registerRequest.setEmail("ope@gmail.com");
        registerRequest.setAddress("yaba mowe");
        registerRequest.setPhoneNumber("66t77253827673");
        registerRequest.setAge("25 years");
        clientService.register(registerRequest);
        assertThrows(UserExistException.class,()-> clientService.register(registerRequest));
    }
    @Test
    public void testThatIfClientTriesToLoginWithWrongEmailAddressThrowsException(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setFirstName("ope");
        registerRequest.setLastName("Mr Tobi");
        registerRequest.setPassword("Opedert13@");
        registerRequest.setEmail("ope@gmail.com");
        registerRequest.setAddress("yaba mowe");
        registerRequest.setPhoneNumber("66t77253827673");
        registerRequest.setAge("25 years");
        clientService.register(registerRequest);

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setClientEmail("ope@gmail.com");
        loginRequest.setPassword("Opedert");
        assertThrows(InvalidLoginException.class,()->clientService.login(loginRequest));
    }
    @Test
    public void testThatUserCantBookAServiceTheServiceOfAServiceProviderThatIsNotAvailable(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setFirstName("ope");
        registerRequest.setLastName("Mr Tobi");
        registerRequest.setPassword("Ope13@");
        registerRequest.setEmail("ope@gmail.com");
        registerRequest.setAddress("yaba mowe");
        registerRequest.setPhoneNumber("66t77253827673");
        registerRequest.setAge("25 years");
        clientService.register(registerRequest);

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setClientEmail("ope@gmail.com");
        loginRequest.setPassword("Ope13@");
        clientService.login(loginRequest);

        registerRequest.setEmail("opeoluwaagnes@gmail.com");

        serviceProviderServices.register(registerRequest);

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
        registerRequest.setFirstName("ope");
        registerRequest.setLastName("Mr Tobi");
        registerRequest.setPassword("Ope13@");
        registerRequest.setEmail("ope@gmail.com");
        registerRequest.setAddress("yaba mowe");
        registerRequest.setPhoneNumber("66t77253827673");
        registerRequest.setAge("25 years");
        clientService.register(registerRequest);

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setClientEmail("ope@gmail.com");
        loginRequest.setPassword("Ope13@");
        clientService.login(loginRequest);

        registerRequest.setEmail("opeoluwaagnes@gmail.com");

        serviceProviderServices.register(registerRequest);

        BookRequest bookRequest = new BookRequest();
        bookRequest.setServiceProviderEmail("opeoluwaagnes@gmail.com");
        bookRequest.setDescription("your service as hairstylist");
        bookRequest.setTime("2 hours");
        bookRequest.setClientEmail("ope@gmail.com");
        clientService.bookServices(bookRequest);
        FindABookRequest findABookRequest = new FindABookRequest();
        findABookRequest.setEmail("ope@gmail.com");
        findABookRequest.setBookId("BK0");
        Book book = clientService.findABookRequest(findABookRequest);
        assertSame(BookType.NOTACCEPTED,book.getAcceptedProject());

        AcceptBookingRequest acceptBookingRequest = new AcceptBookingRequest();
        acceptBookingRequest.setId("BK0");
        acceptBookingRequest.setEmail("opeoluwaagnes@gmail.com");
        acceptBookingRequest.setResponse("accepted");
        serviceProviderServices.acceptClientBookRequest(acceptBookingRequest);


        findABookRequest = new FindABookRequest();
        findABookRequest.setEmail("ope@gmail.com");
        findABookRequest.setBookId("BK0");
        book = clientService.findABookRequest(findABookRequest);
        assertSame(BookType.ACCEPTED,book.getAcceptedProject());

    }

}