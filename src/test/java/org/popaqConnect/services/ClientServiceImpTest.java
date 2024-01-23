package org.popaqConnect.services;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.popaqConnect.data.repositories.ClientRepository;
import org.popaqConnect.dtos.requests.RegisterRequest;
import org.popaqConnect.exceptions.InvalidDetailsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ClientServiceImpTest {
    @Autowired
    ClientService clientService;
    @Autowired
    ClientRepository clientRepository;

    @AfterEach
    public void doThisAfterEachTest(){
        clientRepository.deleteAll();
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
}