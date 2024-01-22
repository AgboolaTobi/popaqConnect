package org.popaqConnect.services;

import lombok.extern.slf4j.Slf4j;
import org.popaqConnect.data.models.Client;
import org.popaqConnect.data.repositories.ClientRepository;
import org.popaqConnect.dtos.requests.RegisterRequest;
import org.popaqConnect.exceptions.InvalidDetailsException;
import org.popaqConnect.exceptions.UserExistException;
import org.popaqConnect.services.ServiceInterfaces.ClientService;
import org.popaqConnect.services.ServiceInterfaces.UserService;
import org.popaqConnect.utils.Mapper;
import org.popaqConnect.utils.VerifyPassword;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ClientServiceImp implements ClientService {
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    UserService userService;

    @Override
    public void register(RegisterRequest registerRequest) {
        log.info("1");
        if(userExist(registerRequest.getEmail()))throw new UserExistException("User exist");
        log.info("2");
        if(!VerifyPassword.verifyPassword(registerRequest.getPassword()))throw new InvalidDetailsException("Wrong password format");
        log.info("3");
        if(!VerifyPassword.verifyEmail(registerRequest.getEmail()))throw new InvalidDetailsException("Invalid email format");
        log.info("4");
        Client newClient = Mapper.mapClient(registerRequest);
        log.info("5");
        clientRepository.save(newClient);
//        log.info("6");
//        userService.save(newClient);
    }

    private boolean userExist(String email){
        Client client = clientRepository.findByEmail(email);
        return client!=null;
    }


}
