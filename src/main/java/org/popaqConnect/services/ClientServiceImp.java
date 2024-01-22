package org.popaqConnect.services;

import lombok.extern.slf4j.Slf4j;
import org.popaqConnect.data.models.Client;
import org.popaqConnect.data.repositories.ClientRepository;
import org.popaqConnect.data.repositories.UserRepository;
import org.popaqConnect.dtos.requests.LoginRequest;
import org.popaqConnect.dtos.requests.RegisterRequest;
import org.popaqConnect.exceptions.InvalidDetailsException;
import org.popaqConnect.exceptions.InvalidLoginException;
import org.popaqConnect.exceptions.UserExistException;
import org.popaqConnect.utils.Mapper;
import org.popaqConnect.utils.VerifyPassword;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ClientServiceImp implements ClientService{
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    UserService userService;

    @Override
    public void register(RegisterRequest registerRequest) {
        if(userExist(registerRequest.getEmail()))throw new UserExistException("User exist");
        if(!VerifyPassword.verifyPassword(registerRequest.getPassword()))throw new InvalidDetailsException("Wrong password format");
        if(!VerifyPassword.verifyEmail(registerRequest.getEmail()))throw new InvalidDetailsException("Invalid email format");
        Client newClient = Mapper.mapClient(registerRequest);
        clientRepository.save(newClient);
        userService.save(newClient);
    }

    @Override
    public void login(LoginRequest loginRequest) {
        Client client = clientRepository.findByEmail(loginRequest.clientEmail);
        if (client == null) throw new InvalidLoginException("Invalid login details");
        if(!verifyLoginPassword(loginRequest.getPassword(), loginRequest.getClientEmail()))throw new InvalidLoginException("Invalid login details");
        client.setLoginStatus(true);
        clientRepository.save(client);
    }

    private boolean userExist(String email){
        Client client = clientRepository.findByEmail(email);
        return client!=null;
    }
    private boolean verifyLoginPassword(String password,String email){
        Client client = clientRepository.findByEmail(email);
        if(client.getPassword() == password)return true;

        return false;
    }


}
