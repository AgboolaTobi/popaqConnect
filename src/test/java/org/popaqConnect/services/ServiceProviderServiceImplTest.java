package org.popaqConnect.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.popaqConnect.data.BookType;
import org.popaqConnect.data.JobCategory;
import org.popaqConnect.data.models.Book;
import org.popaqConnect.data.models.Job;
import org.popaqConnect.data.models.ServiceProvider;
import org.popaqConnect.data.repositories.ClientRepository;
import org.popaqConnect.data.repositories.JobRepository;
import org.popaqConnect.data.repositories.ServiceProviderRepository;
import org.popaqConnect.dtos.requests.*;
import org.popaqConnect.dtos.response.BookResponse;
import org.popaqConnect.exceptions.InvalidDetailsException;
import org.popaqConnect.exceptions.InvalidLoginException;
import org.popaqConnect.exceptions.UserExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ServiceProviderServiceImplTest {
   @Autowired
   ServiceProviderServices service;
   @Autowired
   ServiceProviderRepository serviceProviderRepository;
   @Autowired
   JobRepository jobRepository;
   @Autowired
   BookServices bookServices;
   @Autowired
   ClientService clientService;
   @Autowired
    ClientRepository clientRepository;
   @BeforeEach
   public void deleteThisAfterEachTest(){
      serviceProviderRepository.deleteAll();
      jobRepository.deleteAll();
      clientRepository.deleteAll();
   }

   @Test
   public void serviceProvider_RegisterWithWrong_PasswordFormatThrowExceptionTest(){
      ServiceProviderRegisterRequest registerRequest = new ServiceProviderRegisterRequest();
      registerRequest.setUserName("ope");
      registerRequest.setPassword("1234");
      registerRequest.setEmail("uoidhshdgtytdwgy");
      registerRequest.setAddress("yaba mowe");
      registerRequest.setPhoneNumber("66t77253827673");
      registerRequest.setYearsOfExperience(2);
      registerRequest.setBioData("i an philip i am a software engineer");
      registerRequest.setChargePerHour(2500.00);
      registerRequest.setCategory("ENGINEER");
      registerRequest.setJobTitle("Software engineer");
      assertThrows(InvalidDetailsException.class, ()-> service.register(registerRequest));

   }
   @Test
   public void serviceProvider_RegisterWithWrongEmailFormatThrowExceptionTest(){
      ServiceProviderRegisterRequest registerRequest = new ServiceProviderRegisterRequest();
      registerRequest.setUserName("ope");
      registerRequest.setPassword("PhilipOdey@75");
      registerRequest.setEmail("philipodey75@gmail.com");
      registerRequest.setAddress("yaba mowe");
      registerRequest.setPhoneNumber("66t77253827673");
      registerRequest.setYearsOfExperience(2);
      registerRequest.setBioData("i an philip i am a software engineer");
      registerRequest.setChargePerHour(2500.00);
      registerRequest.setCategory("ENGINEER");
      registerRequest.setJobTitle("Software engineer");
      assertThrows(InvalidDetailsException.class, ()-> service.register(registerRequest));
   }

   @Test
   public void serviceProviderRegisterWithInvalidPhoneNumberFormatThrowExceptionTest(){
      ServiceProviderRegisterRequest registerRequest = new ServiceProviderRegisterRequest();
      registerRequest.setUserName("ope");
      registerRequest.setPassword("PhilipOdey@75");
      registerRequest.setEmail("philipodey75@gmail.com");
      registerRequest.setAddress("yaba mowe");
      registerRequest.setPhoneNumber("sdfvdffvs");
      registerRequest.setYearsOfExperience(2);
      registerRequest.setBioData("i an philip i am a software engineer");
      registerRequest.setChargePerHour(2500.00);
      registerRequest.setCategory("ENGINEER");
      registerRequest.setJobTitle("Software engineer");
      assertThrows(InvalidDetailsException.class, ()-> service.register(registerRequest));
   }
   @Test
   public void serviceProvider_Registers_TrysToRegisterWithTheSameDetailsThrowsExceptionTest(){
      ServiceProviderRegisterRequest registerRequest = new ServiceProviderRegisterRequest();
      registerRequest.setUserName("ope");
      registerRequest.setPassword("PhilipOdey@75");
      registerRequest.setEmail("philipodey75@gmail.com");
      registerRequest.setAddress("yaba mowe");
      registerRequest.setPhoneNumber("+2349019539651");
      registerRequest.setYearsOfExperience(2);
      registerRequest.setBioData("i an philip i am a software engineer");
      registerRequest.setChargePerHour(2500.00);
      registerRequest.setCategory("ENGINEER");
      registerRequest.setJobTitle("Software engineer");
      service.register(registerRequest);
      assertThrows(UserExistException.class, ()-> service.register(registerRequest));
   }
   @Test
   public void registerServiceProvider_WithTheRightDetails_LoginWithTheWrongEmailThrowException(){
      ServiceProviderRegisterRequest registerRequest = new ServiceProviderRegisterRequest();
      registerRequest.setUserName("ope");
      registerRequest.setPassword("PhilipOdey@75");
      registerRequest.setEmail("philipodey75@gmail.com");
      registerRequest.setAddress("yaba mowe");
      registerRequest.setPhoneNumber("+2349019539651");
      registerRequest.setYearsOfExperience(2);
      registerRequest.setBioData("i an philip i am a software engineer");
      registerRequest.setChargePerHour(2500.00);
      registerRequest.setCategory("ENGINEER");
      registerRequest.setJobTitle("Software engineer");
      service.register(registerRequest);
      LoginRequest loginRequest = new LoginRequest();
      loginRequest.setEmail("philipodjjley75@gmail.com");
      loginRequest.setPassword("PhilipOdey@75");
      assertThrows(InvalidLoginException.class, ()->  service.login(loginRequest));
   }
    @Test
   public void registerServiceProvider_WithTheRightDetails_LoginWithTheWrongPasswordThrowException() {
       ServiceProviderRegisterRequest registerRequest = new ServiceProviderRegisterRequest();
       registerRequest.setUserName("ope");
       registerRequest.setPassword("PhilipOdey@75");
       registerRequest.setEmail("philipodey75@gmail.com");
       registerRequest.setAddress("yaba mowe");
       registerRequest.setPhoneNumber("+2349019539651");
       registerRequest.setYearsOfExperience(2);
       registerRequest.setBioData("i an philip i am a software engineer");
       registerRequest.setChargePerHour(2500.00);
       registerRequest.setCategory("ENGINEER");
       registerRequest.setJobTitle("Software engineer");
       service.register(registerRequest);
       LoginRequest loginRequest = new LoginRequest();
       loginRequest.setEmail("philipodey75@gmail.com");
       loginRequest.setPassword("Ope5y5xv@");
       assertThrows(InvalidDetailsException.class, () -> service.login(loginRequest));
    }
    @Test
   public  void registerServiceProvider_LoginServiceProvider_RegisterClient_loginClient_ClientBook_ServiceProviderAccept_CompleteJobStatusTest(){
        ServiceProviderRegisterRequest registerRequest = new ServiceProviderRegisterRequest();
        registerRequest.setUserName("ope");
        registerRequest.setPassword("PhilipOdey@75");
        registerRequest.setEmail("philipodey75@gmail.com");
        registerRequest.setAddress("yaba mowe");
        registerRequest.setPhoneNumber("+2349019539651");
        registerRequest.setYearsOfExperience(2);
        registerRequest.setBioData("i an philip i am a software engineer");
        registerRequest.setChargePerHour(2500.00);
        registerRequest.setCategory("ENGINEER");
        registerRequest.setJobTitle("Software engineer");
        service.register(registerRequest);
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("philipodey75@gmail.com");
        loginRequest.setPassword("PhilipOdey@75");

        RegisterRequest clientRegisterRequest = new RegisterRequest();
        clientRegisterRequest.setUserName("ope");
        clientRegisterRequest.setPassword("Opetobi@12");
        clientRegisterRequest.setEmail("Opetobi13@gmail.com");
        clientRegisterRequest.setAddress("5, Agboola street mafoluku oshodi, Lagos");
        clientRegisterRequest.setPhoneNumber("08025287727");
        clientService.register(clientRegisterRequest);

        ClientLoginRequest clientLoginRequest = new ClientLoginRequest();
        clientLoginRequest.setEmail("Opetobi13@gmail.com");
        clientLoginRequest.setPassword("Opetobi@12");
        clientService.login(clientLoginRequest);


        BookRequest bookRequest = new BookRequest();
        bookRequest.setServiceProviderEmail("philipodey75@gmail.com");
        bookRequest.setDescription("I need a software engineer");
        bookRequest.setTime("3 hours");
        bookRequest.setClientEmail("Opetobi13@gmail.com");
        bookServices.save(bookRequest);


        BookResponse bookingId = clientService.bookServices(bookRequest);
        FindABookRequest findABookRequest = new FindABookRequest();
        findABookRequest.setEmail("Opetobi13@gmail.com");
        findABookRequest.setBookId(bookingId.getMessage());
        Book book = clientService.findABookRequest(findABookRequest);
        assertSame(BookType.NOTACCEPTED,book.getProjectStatus());

        AcceptBookingRequest acceptBookingRequest = new AcceptBookingRequest();
        acceptBookingRequest.setId(bookingId.getMessage());
        acceptBookingRequest.setEmail("philipodey75@gmail.com");
        acceptBookingRequest.setResponse("accepted");
        service.acceptClientBookRequest(acceptBookingRequest);

        book = clientService.findABookRequest(findABookRequest);
        assertSame(BookType.ACCEPTED,book.getProjectStatus());


        CompleteJobRequest completeJobRequest = new CompleteJobRequest();
        completeJobRequest.setJobStatus("completed");
        completeJobRequest.setEmail("philipodey75@gmail.com");
        completeJobRequest.setBookId(bookingId.getMessage());
        service.completeJobStatus(completeJobRequest);
        book = clientService.findABookRequest(findABookRequest);
        assertSame(BookType.COMPLETED, book.getProjectStatus());
    }
     @Test
   public  void registerServiceProvider_LoginServiceProvider_RegisterClient_loginClient_ClientBook_ServiceProviderCancelTest(){
        ServiceProviderRegisterRequest registerRequest = new ServiceProviderRegisterRequest();
        registerRequest.setUserName("ope");
        registerRequest.setPassword("PhilipOdey@75");
        registerRequest.setEmail("philipodey75@gmail.com");
        registerRequest.setAddress("yaba mowe");
        registerRequest.setPhoneNumber("+2349019539651");
        registerRequest.setYearsOfExperience(2);
        registerRequest.setBioData("i an philip i am a software engineer");
        registerRequest.setChargePerHour(2500.00);
        registerRequest.setCategory("ENGINEER");
        registerRequest.setJobTitle("Software engineer");
        service.register(registerRequest);
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("philipodey75@gmail.com");
        loginRequest.setPassword("PhilipOdey@75");

        RegisterRequest clientRegisterRequest = new RegisterRequest();
        clientRegisterRequest.setUserName("ope");
        clientRegisterRequest.setPassword("Opetobi@12");
        clientRegisterRequest.setEmail("Opetobi13@gmail.com");
        clientRegisterRequest.setAddress("5, Agboola street mafoluku oshodi, Lagos");
        clientRegisterRequest.setPhoneNumber("08025287727");
        clientService.register(clientRegisterRequest);

        ClientLoginRequest clientLoginRequest = new ClientLoginRequest();
        clientLoginRequest.setEmail("Opetobi13@gmail.com");
        clientLoginRequest.setPassword("Opetobi@12");
        clientService.login(clientLoginRequest);


        BookRequest bookRequest = new BookRequest();
        bookRequest.setServiceProviderEmail("philipodey75@gmail.com");
        bookRequest.setDescription("I need a software engineer");
        bookRequest.setTime("3 hours");
        bookRequest.setClientEmail("Opetobi13@gmail.com");
        bookServices.save(bookRequest);


        BookResponse bookingId = clientService.bookServices(bookRequest);
        FindABookRequest findABookRequest = new FindABookRequest();
        findABookRequest.setEmail("Opetobi13@gmail.com");
        findABookRequest.setBookId(bookingId.getMessage());
        Book book = clientService.findABookRequest(findABookRequest);
        assertSame(BookType.NOTACCEPTED,book.getProjectStatus());



        CancelBookingRequest cancelBookingRequest = new CancelBookingRequest();
        cancelBookingRequest.setJobStatus("cancel");
        cancelBookingRequest.setBookId(bookingId.getMessage());
        cancelBookingRequest.setEmail("philipodey75@gmail.com");
        service.cancelClientBookRequest(cancelBookingRequest);
        book = clientService.findABookRequest(findABookRequest);
        assertSame(BookType.CANCEL, book.getProjectStatus());
    }
    @Test
    public void registerServiceProvider_loginServiceProvider_UpdateDetails_LoginWithUpdatedDetailsTest(){
        ServiceProviderRegisterRequest registerRequest = new ServiceProviderRegisterRequest();
        registerRequest.setUserName("ope");
        registerRequest.setPassword("PhilipOdey@75");
        registerRequest.setEmail("philipodey75@gmail.com");
        registerRequest.setAddress("yaba mowe");
        registerRequest.setPhoneNumber("+2349019539651");
        registerRequest.setYearsOfExperience(2);
        registerRequest.setBioData("i an philip i am a software engineer");
        registerRequest.setChargePerHour(2500.00);
        registerRequest.setCategory("ENGINEER");
        registerRequest.setJobTitle("Software engineer");
        service.register(registerRequest);
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("philipodey75@gmail.com");
        loginRequest.setPassword("PhilipOdey@75");

        UpdateProfileRequest updateProfileRequest = new UpdateProfileRequest();
//        updateProfileRequest.setUsername("Philip");
        updateProfileRequest.setPassword("Opetobi@75");
        updateProfileRequest.setUpdatedEmail("opetobi34@gmail.com");
        updateProfileRequest.setPreviousEmail("philipodey75@gmail.com");
        service.updateDetails(updateProfileRequest);

        service.logout("opetobi34@gmail.com");
        Optional <ServiceProvider> serviceProvider = service.findUser("opetobi34@gmail.com");
        assertFalse(serviceProvider.get().isLoginStatus());

        loginRequest.setPassword("PhilipOdey@75");
        loginRequest.setEmail("opetobi34@gmail.com");
        assertThrows(InvalidDetailsException.class, ()-> service.login(loginRequest));

    }
    @Test
    public void registerServiceProvider_LoginServiceProvider_LogoutCheckLoginStatusTest(){
        ServiceProviderRegisterRequest registerRequest = new ServiceProviderRegisterRequest();
        registerRequest.setUserName("ope");
        registerRequest.setPassword("PhilipOdey@75");
        registerRequest.setEmail("philipodey75@gmail.com");
        registerRequest.setAddress("yaba mowe");
        registerRequest.setPhoneNumber("+2349019539651");
        registerRequest.setYearsOfExperience(2);
        registerRequest.setBioData("i an philip i am a software engineer");
        registerRequest.setChargePerHour(2500.00);
        registerRequest.setCategory("ENGINEER");
        registerRequest.setJobTitle("Software engineer");
        service.register(registerRequest);

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("philipodey75@gmail.com");
        loginRequest.setPassword("PhilipOdey@75");
        service.login(loginRequest);

        Optional<ServiceProvider> serviceProvider = service.findUser("philipodey75@gmail.com");
        assertTrue(serviceProvider.get().isLoginStatus());

        service.logout("philipodey75@gmail.com");

        serviceProvider = service.findUser("philipodey75@gmail.com");
        assertFalse(serviceProvider.get().isLoginStatus());
    }
     @Test
    public void registerServiceProvider_LoginServiceProvider_LogoutCheckLoginStatus_LoginCheckLoginStatusTest(){
        ServiceProviderRegisterRequest registerRequest = new ServiceProviderRegisterRequest();
        registerRequest.setUserName("ope");
        registerRequest.setPassword("PhilipOdey@75");
        registerRequest.setEmail("philipodey75@gmail.com");
        registerRequest.setAddress("yaba mowe");
        registerRequest.setPhoneNumber("+2349019539651");
        registerRequest.setYearsOfExperience(2);
        registerRequest.setBioData("i an philip i am a software engineer");
        registerRequest.setChargePerHour(2500.00);
        registerRequest.setCategory("ENGINEER");
        registerRequest.setJobTitle("Software engineer");
        service.register(registerRequest);

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("philipodey75@gmail.com");
        loginRequest.setPassword("PhilipOdey@75");
        service.login(loginRequest);

        Optional<ServiceProvider> serviceProvider = service.findUser("philipodey75@gmail.com");
        assertTrue(serviceProvider.get().isLoginStatus());

        service.logout("philipodey75@gmail.com");

        serviceProvider = service.findUser("philipodey75@gmail.com");
        assertFalse(serviceProvider.get().isLoginStatus());
        service.login(loginRequest);

         serviceProvider = service.findUser("philipodey75@gmail.com");
         assertTrue(serviceProvider.get().isLoginStatus());
    }

}
