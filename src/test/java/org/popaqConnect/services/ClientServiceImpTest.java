package org.popaqConnect.services;

import org.junit.jupiter.api.Test;
import org.popaqConnect.dtos.requests.LoginRequest;
import org.popaqConnect.dtos.requests.RegisterRequest;
import org.popaqConnect.exceptions.InvalidDetailsException;
import org.popaqConnect.exceptions.InvalidLoginException;
import org.popaqConnect.exceptions.UserExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ClientServiceImpTest {
    @Autowired
    ClientService clientService;

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


}