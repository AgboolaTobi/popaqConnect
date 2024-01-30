package org.popaqConnect.services;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.popaqConnect.data.CourseStatus;
import org.popaqConnect.data.models.CourseApplication;
import org.popaqConnect.data.models.ServiceProvider;
import org.popaqConnect.data.models.Trainee;
import org.popaqConnect.data.repositories.ServiceProviderRepository;
import org.popaqConnect.data.repositories.TraineeRepository;
import org.popaqConnect.dtos.requests.*;
import org.popaqConnect.dtos.response.ApplyForTrainingResponse;
import org.popaqConnect.exceptions.InvalidDetailsException;
import org.popaqConnect.exceptions.UserDoesNotExistException;
import org.popaqConnect.services.Trainee.TraineeService;
import org.popaqConnect.services.serviceProvider.ServiceProviderServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TraineeServiceImplTest {

    @Autowired
    private TraineeService traineeService;
    @Autowired
    private TraineeRepository traineeRepository;

    @Autowired
    private ServiceProviderServices service;
    @Autowired
    private ServiceProviderRepository providerRepository;

    @AfterEach
    public void doThisAfterEachTest() {
        providerRepository.deleteAll();
        traineeRepository.deleteAll();
    }

    @Test
    public void ifTraineeRegisterWithInvalidPasswordFormatThrowsAndExceptionTest() {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUserName("ope");
        registerRequest.setPassword("1234");
        registerRequest.setEmail("uoidhshdgtytdwgy");
        registerRequest.setAddress("yaba mowe");
        registerRequest.setPhoneNumber("66t77253827673");
        assertThrows(InvalidDetailsException.class, () -> traineeService.register(registerRequest));
    }

    @Test
    public void testThatIfClientRegistersWithInvalidEmailFormatThrowsAndException() {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUserName("ope");
        registerRequest.setPassword("Opedert13@");
        registerRequest.setEmail("ope@");
        registerRequest.setAddress("yaba mowe");
        registerRequest.setPhoneNumber("66t77253827673");
        assertThrows(InvalidDetailsException.class, () -> traineeService.register(registerRequest));
    }

    @Test
    public void traineeLoginWithInvalidEmailTest() {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUserName("ope");
        registerRequest.setPassword("Iniestajnr1");
        registerRequest.setEmail("ope@gmail.com");
        registerRequest.setAddress("yaba mowe");
        registerRequest.setPhoneNumber("+23489447913");
        traineeService.register(registerRequest);
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("qudusa55@gmail.com");
        loginRequest.setPassword("Iniestajnr1");
        assertThrows(UserDoesNotExistException.class, () -> traineeService.login(loginRequest));
    }

    @Test
    public void traineeLoginWithWrongPasswordTest() {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUserName("ope");
        registerRequest.setPassword("Iniestajnr1");
        registerRequest.setEmail("ope@gmail.com");
        registerRequest.setAddress("yaba mowe");
        registerRequest.setPhoneNumber("+23489447913");
        traineeService.register(registerRequest);
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("ope@gmail.com");
        loginRequest.setPassword("234r4");
        assertThrows(InvalidDetailsException.class, () -> traineeService.login(loginRequest));
    }

    @Test
    public void traineeLoginWithRightDetailsTest() {
        Trainee trainee = new Trainee();
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUserName("ope");
        registerRequest.setPassword("Iniestajnr1");
        registerRequest.setEmail("ope@gmail.com");
        registerRequest.setAddress("yaba mowe");
        registerRequest.setPhoneNumber("09089447913");
        traineeService.register(registerRequest);

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("ope@gmail.com");
        loginRequest.setPassword("Iniestajnr1");
        traineeService.login(loginRequest);
        assertTrue(trainee.isLoginStatus());
    }

    @Test
    public void testThatWhenATraineeApplyToBeTrained() {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUserName("ope");
        registerRequest.setPassword("Iniestajnr1");
        registerRequest.setEmail("ope@gmail.com");
        registerRequest.setAddress("yaba mowe");
        registerRequest.setPhoneNumber("09089447913");
        traineeService.register(registerRequest);
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("ope@gmail.com");
        loginRequest.setPassword("Iniestajnr1");
        traineeService.login(loginRequest);

        ServiceProviderRegisterRequest registerRequests = new ServiceProviderRegisterRequest();
        registerRequests.setUserName("Mr tobi");
        registerRequests.setPassword("PhilipOdey@75");
        registerRequests.setEmail("opeoluwaagnes@gmail.com");
        registerRequests.setAddress("yaba mowe");
        registerRequests.setPhoneNumber("+2349019539651");
        registerRequests.setYearsOfExperience(2);
        registerRequests.setBioData("i an philip i am a software engineer");
        registerRequests.setChargePerHour(2500.00);
        registerRequests.setCategory("ENGINEER");
        registerRequests.setJobTitle("Software engineer");

        service.register(registerRequests);

        LoginRequest loginRequests = new LoginRequest();
        loginRequests.setEmail("opeoluwaagnes@gmail.com");
        loginRequests.setPassword("PhilipOdey@75");
        service.login(loginRequests);

        Optional<ServiceProvider> serviceProvider = service.findUser(loginRequests.getEmail());
        serviceProvider.get().setAvailableForTraining(true);
        providerRepository.save(serviceProvider.get());

        TrainingRequest trainingApplication = new TrainingRequest();

        trainingApplication.setTraineeEmail("ope@gmail.com");
        trainingApplication.setStartDate("1/2/2024");
        trainingApplication.setEndDate("2/4/2024");
        trainingApplication.setTrainerEmail("opeoluwaagnes@gmail.com");
        trainingApplication.setAboutYou("my name is qudus,i have no pior knowledege to this, i hope you consider me");
        ApplyForTrainingResponse trainingResponse = traineeService.applyForTraining(trainingApplication);
        assertNotNull(trainingResponse);

    }

    @Test
    public void testThatWhenTraineeSearchForAvailableTrainerIfNoOneIsAvailableListSizeIsZero() {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUserName("ope");
        registerRequest.setPassword("Iniestajnr1");
        registerRequest.setEmail("ope@gmail.com");
        registerRequest.setAddress("yaba mowe");
        registerRequest.setPhoneNumber("09089447913");
        traineeService.register(registerRequest);


        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("ope@gmail.com");
        loginRequest.setPassword("Iniestajnr1");
        traineeService.login(loginRequest);

        ServiceProviderRegisterRequest registerRequests = new ServiceProviderRegisterRequest();
        registerRequests.setUserName("Mr tobi");
        registerRequests.setPassword("PhilipOdey@75");
        registerRequests.setEmail("opeoluwaagnes@gmail.com");
        registerRequests.setAddress("yaba mowe");
        registerRequests.setPhoneNumber("+2349019539651");
        registerRequests.setYearsOfExperience(2);
        registerRequests.setBioData("i an philip i am a software engineer");
        registerRequests.setChargePerHour(2500.00);
        registerRequests.setCategory("ENGINEER");
        registerRequests.setJobTitle("Software engineer");

        service.register(registerRequests);

        LoginRequest loginRequests = new LoginRequest();
        loginRequests.setEmail("opeoluwaagnes@gmail.com");
        loginRequests.setPassword("PhilipOdey@75");
        service.login(loginRequests);
        List<ServiceProvider> serviceProviders = traineeService.searchForTrainers("ope@gmail.com");
        assertEquals(0, serviceProviders.size());

    }

    @Test
    public void testThatWhenTraineeCancelCourseCourseStatusChangeToCancelled() {
        RegisterRequest request = new RegisterRequest();
        request.setUserName("philip");
        request.setPassword("Iniestajnr1");
        request.setEmail("ope@gmail.com");
        request.setAddress("yaba mowe");
        request.setPhoneNumber("09089447913");
        traineeService.register(request);


        LoginRequest loginRequests = new LoginRequest();
        loginRequests.setEmail("ope@gmail.com");
        loginRequests.setPassword("Iniestajnr1");
        traineeService.login(loginRequests);

        ServiceProviderRegisterRequest registerRequest = new ServiceProviderRegisterRequest();
        registerRequest.setUserName("Mr tobi");
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

        Optional<ServiceProvider> serviceProvider = service.findUser(loginRequest.getEmail());
        serviceProvider.get().setAvailableForTraining(true);
        providerRepository.save(serviceProvider.get());

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
        providerRepository.save(serviceProvider.get());

        CancelCourseRequest cancelCourseRequest = new CancelCourseRequest();
        cancelCourseRequest.setTraineeEmail("ope@gmail.com");
        cancelCourseRequest.setCourseCode(trainingResponse.getMessage());
        cancelCourseRequest.setTrainerEmail("philipodey75@gmail.com");
        traineeService.cancelCourseApplication(cancelCourseRequest);

        ViewCourseApplicationRequest viewCourseApplication = new ViewCourseApplicationRequest();
        viewCourseApplication.setCourseCode(trainingResponse.getMessage());
        viewCourseApplication.setTraineeEmail("ope@gmail.com");
        CourseApplication course = traineeService.viewCourseApplication(viewCourseApplication);
        assertSame(CourseStatus.CANCELED, course.getCourseStatus());
    }

    @Test
    public void register_Trainee_login_Trainee_Update_TraineeStatusTryLoginWithUpdatedDetailsTest() {
        RegisterRequest request = new RegisterRequest();
        request.setUserName("philip");
        request.setPassword("Iniestajnr1");
        request.setEmail("ope@gmail.com");
        request.setAddress("yaba mowe");
        request.setPhoneNumber("09089447913");
        traineeService.register(request);


        LoginRequest loginRequests = new LoginRequest();
        loginRequests.setEmail("ope@gmail.com");
        loginRequests.setPassword("Iniestajnr1");
        traineeService.login(loginRequests);

        Trainee trainee = traineeRepository.findByEmail(loginRequests.getEmail());
        assertTrue(trainee.isLoginStatus());

        TraineeUpdateProfileRequest updateDetailRequest = new TraineeUpdateProfileRequest();
        updateDetailRequest.setEmail("ope@gmail.com");
        updateDetailRequest.setUpdatedEmail("philipodey@gmail.com");
        updateDetailRequest.setPassword("Philip@1234");
        traineeService.updateProfile(updateDetailRequest);

        traineeService.logout(updateDetailRequest.getUpdatedEmail());

        trainee = traineeRepository.findByEmail(updateDetailRequest.getUpdatedEmail());
        assertFalse(trainee.isLoginStatus());

        loginRequests.setPassword(updateDetailRequest.getPassword());
        loginRequests.setEmail(updateDetailRequest.getUpdatedEmail());
        traineeService.login(loginRequests);

        trainee = traineeRepository.findByEmail(updateDetailRequest.getUpdatedEmail());
        assertTrue(trainee.isLoginStatus());


    }

    @Test
    public void register_Trainee_login_Trainee_Update_TraineeStatusTryLoginWithPreviousDetails_ThrowsExceptionTest() {
        RegisterRequest request = new RegisterRequest();
        request.setUserName("philip");
        request.setPassword("Iniestajnr1");
        request.setEmail("ope@gmail.com");
        request.setAddress("yaba mowe");
        request.setPhoneNumber("09089447913");
        traineeService.register(request);


        LoginRequest loginRequests = new LoginRequest();
        loginRequests.setEmail("ope@gmail.com");
        loginRequests.setPassword("Iniestajnr1");
        traineeService.login(loginRequests);

        Trainee trainee = traineeRepository.findByEmail(loginRequests.getEmail());
        assertTrue(trainee.isLoginStatus());

        TraineeUpdateProfileRequest updateDetailRequest = new TraineeUpdateProfileRequest();
        updateDetailRequest.setEmail("ope@gmail.com");
        updateDetailRequest.setUpdatedEmail("philipodey@gmail.com");
        updateDetailRequest.setPassword("Philip@1234");
        traineeService.updateProfile(updateDetailRequest);

        traineeService.logout(updateDetailRequest.getUpdatedEmail());

        trainee = traineeRepository.findByEmail(updateDetailRequest.getUpdatedEmail());
        assertFalse(trainee.isLoginStatus());

        loginRequests.setPassword(loginRequests.getPassword());
        loginRequests.setEmail(updateDetailRequest.getUpdatedEmail());

        trainee = traineeRepository.findByEmail(updateDetailRequest.getUpdatedEmail());
        assertFalse(trainee.isLoginStatus());

        assertThrows(InvalidDetailsException.class, ()-> traineeService.login(loginRequests));


    }
}

