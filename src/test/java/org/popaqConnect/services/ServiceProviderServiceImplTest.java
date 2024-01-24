package org.popaqConnect.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.popaqConnect.data.JobCategory;
import org.popaqConnect.data.models.Job;
import org.popaqConnect.data.repositories.JobRepository;
import org.popaqConnect.data.repositories.ServiceProviderRepository;
import org.popaqConnect.dtos.requests.LoginRequest;
import org.popaqConnect.dtos.requests.ServiceProviderRegisterRequest;
import org.popaqConnect.exceptions.InvalidDetailsException;
import org.popaqConnect.exceptions.UserExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class ServiceProviderServiceImplTest {
   @Autowired
   ServiceProviderServices service;
   @Autowired
   ServiceProviderRepository serviceProviderRepository;
   @Autowired
   JobRepository jobRepository;
   @BeforeEach
   public void deleteThisAfterEachTest(){
      serviceProviderRepository.deleteAll();
      jobRepository.deleteAll();
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
      assertThrows(UserExistException.class, ()->  service.login(loginRequest));
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


}
