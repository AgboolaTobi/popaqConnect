package org.popaqConnect.services;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.popaqConnect.data.models.Trainee;
import org.popaqConnect.data.repositories.TraineeRepository;
import org.popaqConnect.dtos.requests.LoginRequest;
import org.popaqConnect.dtos.requests.RegisterRequest;
import org.popaqConnect.exceptions.InvalidDetailsException;
import org.popaqConnect.exceptions.UserDoesNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TraineeServiceImplTest {

    @Autowired
    private TraineeService traineeService;
    @Autowired
    private TraineeRepository traineeRepository;

    @AfterEach
    public void doThisAfterEachTest(){
        traineeRepository.deleteAll();
    }
    @Test
    public void ifTraineeRegisterWithInvalidPasswordFormatThrowsAndExceptionTest(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setFirstName("ope");
        registerRequest.setLastName("Mr Tobi");
        registerRequest.setPassword("1234");
        registerRequest.setEmail("uoidhshdgtytdwgy");
        registerRequest.setAddress("yaba mowe");
        registerRequest.setPhoneNumber("66t77253827673");
        assertThrows(InvalidDetailsException.class,()-> traineeService.register(registerRequest));
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
        assertThrows(InvalidDetailsException.class,()-> traineeService.register(registerRequest));
    }
    @Test
    public void traineeLoginWithInvalidEmailTest(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setFirstName("ope");
        registerRequest.setLastName("Mr Tobi");
        registerRequest.setPassword("Iniestajnr1");
        registerRequest.setEmail("ope@gmail.com");
        registerRequest.setAddress("yaba mowe");
        registerRequest.setPhoneNumber("+23489447913");
        registerRequest.setAge("25 years");
        traineeService.register(registerRequest);
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("qudusa55@gmail.com");
        loginRequest.setPassword("Iniestajnr1");
        assertThrows(UserDoesNotExistException.class,()-> traineeService.login(loginRequest));
    }
    @Test
    public void traineeLoginWithWrongPasswordTest(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setFirstName("ope");
        registerRequest.setLastName("Mr Tobi");
        registerRequest.setPassword("Iniestajnr1");
        registerRequest.setEmail("ope@gmail.com");
        registerRequest.setAddress("yaba mowe");
        registerRequest.setPhoneNumber("+23489447913");
        registerRequest.setAge("25 years");
        traineeService.register(registerRequest);
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("ope@gmail.com");
        loginRequest.setPassword("234r4");
        assertThrows(InvalidDetailsException.class,()-> traineeService.login(loginRequest));
    }
    @Test
    public void traineeLoginWithRightDetailsTest(){
        Trainee trainee = new Trainee();
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setFirstName("ope");
        registerRequest.setLastName("Mr Tobi");
        registerRequest.setPassword("Iniestajnr1");
        registerRequest.setEmail("ope@gmail.com");
        registerRequest.setAddress("yaba mowe");
        registerRequest.setPhoneNumber("09089447913");
        registerRequest.setAge("25 years");
        traineeService.register(registerRequest);
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("ope@gmail.com");
        loginRequest.setPassword("Iniestajnr1");
        traineeService.login(loginRequest);
        assertFalse(trainee.isLocked());
    }
}

