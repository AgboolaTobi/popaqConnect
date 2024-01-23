package org.popaqConnect.services;

import org.popaqConnect.data.models.ServiceProvider;
import org.popaqConnect.data.repositories.ServiceProviderRepository;
import org.popaqConnect.dtos.requests.LoginRequest;
import org.popaqConnect.dtos.requests.ServiceProviderRegisterRequest;
import org.popaqConnect.exceptions.InvalidDetailsException;
import org.popaqConnect.exceptions.UserExistException;
import org.popaqConnect.utils.Mapper;
import org.popaqConnect.utils.ServiceProviderMapper;
import org.popaqConnect.utils.VerifyPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceProviderServiceImpl implements ServiceProviderService{
    @Autowired
    ServiceProviderRepository providerRepository;
    @Override
    public void register(ServiceProviderRegisterRequest registerRequest) {
        if(userExist(registerRequest.getEmail())) throw new UserExistException(registerRequest.getEmail() + " already exist!!!");
        if (!VerifyPassword.verifyEmail(registerRequest.getEmail())) throw new InvalidDetailsException("Invalid email format!!");
        if(!VerifyPassword.verifyPassword(registerRequest.getPassword())) throw new InvalidDetailsException("Invalid password format!!!");
        if (!VerifyPassword.verifyPhoneNumber(registerRequest.getPhoneNumber())) throw new InvalidDetailsException("Invalid phone number");
        ServiceProvider newServiceProvider = ServiceProviderMapper.serviceProviderMap(registerRequest);
        providerRepository.save(newServiceProvider);
    }

    @Override
    public void login(LoginRequest loginRequest) {

        ServiceProvider serviceProvider = providerRepository.findByEmail(loginRequest.getEmail());
        if (!userExist(loginRequest.getEmail())) throw new UserExistException("User does not exist");
//        if (!serviceProvider.getEmail().equals(loginRequest.getEmail())) throw new InvalidDetailsException("Invalid Login Details!!");
        if (!serviceProvider.getPassword().equals(loginRequest.getPassword())) throw new InvalidDetailsException("Invalid Login Details!!");
        providerRepository.save(serviceProvider);
    }

    private boolean userExist(String email) {
        ServiceProvider serviceProvide = providerRepository.findByEmail(email);
        return serviceProvide != null;
    }
}
