package org.popaqConnect.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.popaqConnect.data.CourseStatus;
import org.popaqConnect.data.models.Book;
import org.popaqConnect.data.models.CourseApplication;
import org.popaqConnect.data.models.ServiceProvider;
import org.popaqConnect.data.models.Trainee;
import org.popaqConnect.data.repositories.*;
import org.popaqConnect.dtos.requests.*;
import org.popaqConnect.dtos.response.ApplyForTrainingResponse;
import org.popaqConnect.dtos.response.BookResponse;
import org.popaqConnect.exceptions.*;
import org.popaqConnect.services.Trainee.TraineeService;
import org.popaqConnect.services.client.ClientService;
import org.popaqConnect.services.serviceProvider.ServiceProviderServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
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
   TraineeService traineeService;
   @Autowired
    TraineeRepository traineeRepository;
   @Autowired
    CourseApplicationRepository courseApplicationRepository;
   @Autowired
    ClientService clientService;
   @Autowired
    ClientRepository clientRepository;
   @Autowired
   BookRepository bookRepository;
   private ServiceProviderRegisterRequest registerRequest;

   private LoginRequest loginRequest;
   @BeforeEach
   public void deleteThisAfterEachTest(){
      serviceProviderRepository.deleteAll();
      jobRepository.deleteAll();
      traineeRepository.deleteAll();
      bookRepository.deleteAll();
      courseApplicationRepository.deleteAll();
      clientRepository.deleteAll();
   }
   @BeforeEach
   public  void setUpTraineeAccount(){
       registerRequest = new ServiceProviderRegisterRequest();
       registerRequest.setFirstName("ope");
       registerRequest.setLastName("Mr Tobi");
       registerRequest.setPassword("PhilipOdey@75");
       registerRequest.setEmail("philipodey75@gmail.com");
       registerRequest.setAddress("yaba mowe");
       registerRequest.setPhoneNumber("+2349019539651");
       registerRequest.setYearsOfExperience(2);
       registerRequest.setBioData("i an philip i am a software engineer");
       registerRequest.setChargePerHour(2500.00);
       registerRequest.setCategory("ENGINEER");
       registerRequest.setJobTitle("Software engineer");
   }
   @BeforeEach
   public void loginServiceProvider(){

       loginRequest = new LoginRequest();
       loginRequest.setEmail("philipodey75@gmail.com");
       loginRequest.setPassword("PhilipOdey@75");
   }

   @Test
   public void serviceProvider_RegisterWithWrong_PasswordFormatThrowExceptionTest(){
      ServiceProviderRegisterRequest registerRequest = new ServiceProviderRegisterRequest();
      registerRequest.setFirstName("ope");
      registerRequest.setLastName("Mr Tobi");
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
      registerRequest.setFirstName("ope");
      registerRequest.setLastName("Mr Tobi");
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
      registerRequest.setFirstName("ope");
      registerRequest.setLastName("Mr Tobi");
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
      registerRequest.setFirstName("ope");
      registerRequest.setLastName("Mr Tobi");
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
      registerRequest.setFirstName("ope");
      registerRequest.setLastName("Mr Tobi");
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
      loginRequest.setEmail("philipoddacey75@gmail.com");
      loginRequest.setPassword("Ope5y5xv@");
      assertThrows(InvalidLoginException.class, ()->  service.login(loginRequest));
   }
    @Test
   public void registerServiceProvider_WithTheRightDetails_LoginWithTheWrongPasswordThrowException() {
       ServiceProviderRegisterRequest registerRequest = new ServiceProviderRegisterRequest();
       registerRequest.setFirstName("ope");
       registerRequest.setLastName("Mr Tobi");
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
   public void testThatServiceProviderCanUpdateTheTraineeStatusFromLearningToCompleted(){
       service.register(registerRequest);
        service.login(loginRequest);


        Optional<ServiceProvider> serviceProvider = service.findUser("philipodey75@gmail.com");
        serviceProvider.get().setAvailableForTraining(true);
        serviceProviderRepository.save(serviceProvider.get());

        RegisterRequest request = new RegisterRequest();
        request = new RegisterRequest();
        request.setFirstName("ope");
        request.setLastName("Mr Tobi");
        request.setPassword("Iniestajnr1");
        request.setEmail("ope@gmail.com");
        request.setAddress("yaba mowe");
        request.setPhoneNumber("09089447913");
        request.setAge("25 years");
        traineeService.register(request);

       loginRequest.setEmail("ope@gmail.com");
       loginRequest.setPassword("Iniestajnr1");
       traineeService.login(loginRequest);

        TrainingRequest trainingApplication = new TrainingRequest();

        trainingApplication.setTraineeEmail("ope@gmail.com");
        trainingApplication.setStartDate("1/2/2024");
        trainingApplication.setEndDate("2/4/2024");
        trainingApplication.setTrainerEmail("philipodey75@gmail.com");
        trainingApplication.setAboutYou("my name is qudus,i have no pior knowledege to this, i hope you consider me");
        ApplyForTrainingResponse trainingResponse = traineeService.applyForTraining(trainingApplication);

       ViewTraineeCourseRequest viewTraineeCourseRequest = new ViewTraineeCourseRequest();
       viewTraineeCourseRequest.setTrainerEmail("philipodey75@gmail.com");
       //courseApplicationRequest.setTraineeEmail("ope@gmail.com" );
       viewTraineeCourseRequest.setCourseCode(trainingResponse.getMessage());

       CourseApplication course = service.viewACourseApplication(viewTraineeCourseRequest);

       course.setCourseStatus(CourseStatus.LEARNING);
       courseApplicationRepository.save(course);
       assertSame(CourseStatus.LEARNING,course.getCourseStatus());


       UpdateOnCourseApplicationRequest updateOnCourseApplicationRequest = new UpdateOnCourseApplicationRequest();
       updateOnCourseApplicationRequest.setCourseCode(trainingResponse.getMessage());
       updateOnCourseApplicationRequest.setTrainerEmail("philipodey75@gmail.com");
       updateOnCourseApplicationRequest.setTraineeEmail("ope@gmail.com");
       updateOnCourseApplicationRequest.setUpdateCourseStatus("completed");

       service.updateOnTrainee(updateOnCourseApplicationRequest);

       course = service.viewACourseApplication(viewTraineeCourseRequest);
       assertSame(CourseStatus.COMPLETED,course.getCourseStatus());
   }
   @Test
   public void testThatWhenServiceProviderAsOneTraineeAndHeCancelTheTrainingApplicationListOfHisTraineeIsZero(){
       service.register(registerRequest);
       service.login(loginRequest);
       RegisterRequest request = new RegisterRequest();
       request.setFirstName("ope");
       request.setLastName("Mr Tobi");
       request.setPassword("Iniestajnr1");
       request.setEmail("ope@gmail.com");
       request.setAddress("yaba mowe");
       request.setPhoneNumber("09089447913");
       request.setAge("25 years");
       traineeService.register(request);


       LoginRequest loginRequests = new LoginRequest();
       loginRequests.setEmail("ope@gmail.com");
       loginRequests.setPassword("Iniestajnr1");
       traineeService.login(loginRequests);

       Optional<ServiceProvider> serviceProvider = service.findUser(loginRequest.getEmail());
       serviceProvider.get().setAvailableForTraining(true);
       serviceProviderRepository.save(serviceProvider.get());

       TrainingRequest trainingApplication = new TrainingRequest();

       trainingApplication.setTraineeEmail("ope@gmail.com");
       trainingApplication.setStartDate("1/2/2024");
       trainingApplication.setEndDate("2/4/2024");
       trainingApplication.setTrainerEmail("philipodey75@gmail.com");
       trainingApplication.setAboutYou("my name is qudus,i have no pior knowledege to this, i hope you consider me");
       ApplyForTrainingResponse trainingResponse = traineeService.applyForTraining(trainingApplication);

       Trainee trainee = traineeRepository.findByEmail("ope@gmail.com");
       List<Trainee> trainees = new ArrayList<>();
       trainees.add(trainee);
       serviceProvider.get().setTrainees(trainees);
       serviceProviderRepository.save(serviceProvider.get());

       CancelServiceProviderRequest cancelRequest = new CancelServiceProviderRequest ();
       cancelRequest.setEmail("ope@gmail.com");
       cancelRequest.setId(trainingResponse.getMessage());
       cancelRequest.setServiceProviderEmail("philipodey75@gmail.com");
       service.cancleRequest(cancelRequest);

       ViewTraineeCourseRequest courseApplication = new ViewTraineeCourseRequest();
       courseApplication.setCourseCode(trainingResponse.getMessage());
      // courseApplication.setTraineeEmail("ope@gmail.com");
       courseApplication.setTrainerEmail("philipodey75@gmail.com");
       CourseApplication course = service.viewACourseApplication(courseApplication);

        assertSame(CourseStatus.CANCELED,course.getCourseStatus());
        assertEquals(0,service.findUser(("philipodey75@gmail.com")).get().getTrainees().size());
   }

    @Test
    public void testThatServiceProviderCanFindAllBookingHistory() {
        service.register(registerRequest);
        service.login(loginRequest);

        RegisterRequest request = new RegisterRequest();
        request.setFirstName("ope");
        request.setLastName("Mr Tobi");
        request.setPassword("Ope13@");
        request.setEmail("opeoluwaagnes@gmail.com");
        request.setAddress("yaba mowe");
        request.setPhoneNumber("66t77253827673");
        request.setAge("25 years");
        clientService.register(request);

        ClientLoginRequest loginRequests = new ClientLoginRequest();
        loginRequests.setEmail("opeoluwaagnes@gmail.com");
        loginRequests.setPassword("Ope13@");
        clientService.login(loginRequests);


        BookRequest bookRequest = new BookRequest();
        bookRequest.setServiceProviderEmail("philipodey75@gmail.com");
        bookRequest.setDescription("your service as hairstylist");
        bookRequest.setTime("2 hours");
        bookRequest.setClientEmail("opeoluwaagnes@gmail.com");
        BookResponse bookingId = clientService.bookServices(bookRequest);

        AcceptBookingRequest acceptBookingRequest = new AcceptBookingRequest();
        acceptBookingRequest.setId(bookingId.getMessage());
        acceptBookingRequest.setEmail("philipodey75@gmail.com");
        acceptBookingRequest.setResponse("reject");
        service.acceptClientBookRequest(acceptBookingRequest);

        List<Book> bookingHistory = service.findAllBookingHistory("philipodey75@gmail.com");
        assertEquals(1, bookingHistory.size());
    }
    @Test
    public void testThatWhenServiceProviderTriesToFindABookingHistoryThatDoesntExistThrowsAnException(){
       service.register(registerRequest);
       service.login(loginRequest);
       ViewABookingRequest viewABookingRequest = new ViewABookingRequest();
       viewABookingRequest.setEmail("philipodey75@gmail.com");
       viewABookingRequest.setBookingId("Bk-123s");
       assertThrows(BookingRequestException.class,()->service.viewABookingHistory(viewABookingRequest));
    }

}
