package org.popaqConnect.services;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.popaqConnect.data.models.ServiceProvider;
import org.popaqConnect.data.repositories.ClientRepository;
import org.popaqConnect.dtos.requests.RegisterRequest;
import org.popaqConnect.exceptions.*;
import org.popaqConnect.data.BookType;
import org.popaqConnect.data.models.Book;
import org.popaqConnect.data.models.Client;
import org.popaqConnect.data.repositories.BookRepository;
import org.popaqConnect.data.repositories.ClientRepository;
import org.popaqConnect.data.repositories.ServiceProviderRepository;
import org.popaqConnect.dtos.requests.*;
import org.popaqConnect.dtos.response.BookResponse;
import org.popaqConnect.exceptions.InvalidDetailsException;
import org.popaqConnect.exceptions.InvalidLoginException;
import org.popaqConnect.exceptions.UserExistException;
import org.popaqConnect.services.Booking.BookServices;
import org.popaqConnect.services.client.ClientService;
import org.popaqConnect.services.serviceProvider.ServiceProviderServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

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
        assertThrows(InvalidDetailsException.class,()-> clientService.register(registerRequest));
    }
    @Test
    public void testThatIfClientTriesToLoginWithWrongEmailAddressThrowsException(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUserName("ope");
        registerRequest.setPassword("Opedert13@");
        registerRequest.setEmail("ope@gmail.com");
        registerRequest.setAddress("yaba mowe");
        registerRequest.setPhoneNumber("09079447913");
        clientService.register(registerRequest);
        ClientLoginRequest loginRequest = new ClientLoginRequest();
        loginRequest.setEmail("ope@gmail.com");
        loginRequest.setPassword("Opedert");
        assertThrows(InvalidLoginException.class,()->clientService.login(loginRequest));
    }

    @Test
    public void testThatUserCantBookAServiceTheServiceOfAServiceProviderThatIsNotAvailable() {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUserName("ope");
        registerRequest.setPassword("Ope13@");
        registerRequest.setEmail("ope@gmail.com");
        registerRequest.setAddress("yaba mowe");
        registerRequest.setPhoneNumber("09079447913");
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
        List<Book> clientsBooks = clientService.viewAllBookingHistory("ope@gmail.com");
        assertEquals(1,clientsBooks.size());

    }

    @Test
    public void testThatWhenAClientBookARequestAndServiceProviderIsAvailabilityBecomesFalse(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUserName("ope");
        registerRequest.setPassword("Ope13@");
        registerRequest.setEmail("ope@gmail.com");
        registerRequest.setAddress("yaba mowe");
        registerRequest.setPhoneNumber("66t77253827673");;
        registerRequest.setPhoneNumber("09079447913");
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
        Book book = clientService.viewABookingHistory(findABookRequest);
        assertSame(BookType.NOTACCEPTED,book.getProjectStatus());



        AcceptBookingRequest acceptBookingRequest = new AcceptBookingRequest();
        acceptBookingRequest.setId(bookingId.getMessage());
        acceptBookingRequest.setEmail("opeoluwaagnes@gmail.com");
        acceptBookingRequest.setResponse("reject");
        serviceProviderServices.acceptClientBookRequest(acceptBookingRequest);

        book = clientService.viewABookingHistory(findABookRequest);
        assertSame(BookType.REJECT,book.getProjectStatus());
    }



    @Test
    public void testThatClientCanUpdateDetails(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUserName("Olushola");
        registerRequest.setPassword("Opedert13@");
        registerRequest.setEmail("ope@gmail.com");
        registerRequest.setAddress("yaba mowe");
        registerRequest.setPhoneNumber("+23456789056");
        clientService.register(registerRequest);

        ClientLoginRequest loginRequest = new ClientLoginRequest();
        loginRequest.setEmail("ope@gmail.com");
        loginRequest.setPassword("Opedert13@");
        clientService.login(loginRequest);

        ClientUpdateRequest clientUpdateRequest = new ClientUpdateRequest();

        clientUpdateRequest.setUserName("Ogungbeni");
        clientUpdateRequest.setPassword("Opedert13@");
        clientUpdateRequest.setEmail("ope@gmail.com");
        clientUpdateRequest.setAddress("Sabo Yaba");
        clientUpdateRequest.setPhoneNumber("08068952944");
        clientUpdateRequest.setAddress("30 years");
        clientService.update(clientUpdateRequest);

        assertEquals(clientRepository.findByEmail("ope@gmail.com"), clientRepository.findByEmail("ope@gmail.com"));
    }

    @Test
    public void testThatClientCanLogOut() {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUserName("Olushola");
        registerRequest.setPassword("Opedert13@");
        registerRequest.setEmail("ope@gmail.com");
        registerRequest.setAddress("yaba mowe");
        registerRequest.setPhoneNumber("07066221008");
        clientService.register(registerRequest);

        ClientLoginRequest loginRequest = new ClientLoginRequest();
        loginRequest.setEmail("ope@gmail.com");
        loginRequest.setPassword("Opedert13@");
        clientService.login(loginRequest);

        ClientLogoutRequest clientLogoutRequest = new ClientLogoutRequest();
        clientLogoutRequest.setEmail("ope@gmail.com");
        clientService.logout(clientLogoutRequest);
        Client existingClient = clientRepository.findByEmail("ope@gmail.com");
        assertFalse(existingClient.isLoginStatus());

    }

    @Test
    public void testThatClientCanDeleteAccount() {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUserName("Olushola");
        registerRequest.setPassword("Opedert13@");
        registerRequest.setEmail("ope@gmail.com");
        registerRequest.setAddress("yaba mowe");
        registerRequest.setPhoneNumber("07066221009");
        clientService.register(registerRequest);

        ClientLoginRequest loginRequest = new ClientLoginRequest();
        loginRequest.setEmail("ope@gmail.com");
        loginRequest.setPassword("Opedert13@");
        clientService.login(loginRequest);

        ClientDeleteRequest clientDeleteRequest = new ClientDeleteRequest();
        clientDeleteRequest.setEmail("ope@gmail.com");
        clientDeleteRequest.setPassword("Opedert13@");
        clientService.deleteAccount(clientDeleteRequest);
        Client existingClient = clientRepository.findByEmail("ope@gmail.com");
        assertNull(existingClient);
        assertEquals(0,clientRepository.count());

    }
    @Test
    public void testThatWhenUserCancelBookingRequestTheServiceProviderAvailabilityBecomesFalse(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUserName("ope");
        registerRequest.setPassword("Ope13@");
        registerRequest.setEmail("ope@gmail.com");
        registerRequest.setAddress("yaba mowe");
        registerRequest.setPhoneNumber("+2347066221008");
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
        Book book = clientService.viewABookingHistory(findABookRequest);
        assertSame(BookType.NOTACCEPTED,book.getProjectStatus());


        AcceptBookingRequest acceptBookingRequest = new AcceptBookingRequest();
        acceptBookingRequest.setId(bookingId.getMessage());
        acceptBookingRequest.setEmail("opeoluwaagnes@gmail.com");
        acceptBookingRequest.setResponse("accepted");
        serviceProviderServices.acceptClientBookRequest(acceptBookingRequest);


        ClientCancelBookingRequest cancelRequest = new ClientCancelBookingRequest();
        cancelRequest.setClientEmail("ope@gmail.com");
        cancelRequest.setServiceProviderEmail("opeoluwaagnes@gmail.com");
        cancelRequest.setBookingId(bookingId.getMessage());
        clientService.cancelBookingRequest(cancelRequest);
        Optional<ServiceProvider> seller = serviceProviderServices.findUser("opeoluwaagnes@gmail.com");

         book = clientService.viewABookingHistory(findABookRequest);
        assertSame(BookType.CANCEL,book.getProjectStatus());
        assertTrue(seller.get().isAvailable());


    }

    @Test
    public void searchByDropTitleTest() {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUserName("ope");
        registerRequest.setPassword("Ope13@");
        registerRequest.setEmail("ope@gmail.com");
        registerRequest.setAddress("yaba mowe");
        registerRequest.setPhoneNumber("09079447913");
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

        SearchByDRopTitleRequest search = new SearchByDRopTitleRequest();
        search.setTitle("Software engineer");
        search.setEmail("ope@gmail.com");
        List<ServiceProvider> providers = clientService.searchBYDropTitle(search);
        assertEquals(1, providers.size());
    }

    @Test
    public void searchByCategoryTest() {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUserName("ope");
        registerRequest.setPassword("Ope13@");
        registerRequest.setEmail("ope@gmail.com");
        registerRequest.setAddress("yaba mowe");
        registerRequest.setPhoneNumber("09079447913");
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

        SearchByCategory searchByCategory = new SearchByCategory();
        searchByCategory.setCategory("ENGINEER");
        searchByCategory.setEmail("ope@gmail.com");
        List<ServiceProvider> serviceProviderList = clientService.searchByCategory(searchByCategory);
        assertEquals(1, serviceProviderList.size());
    }
}