package org.popaqConnect.services;

import org.popaqConnect.data.models.Client;
import org.popaqConnect.data.models.Job;
import org.popaqConnect.data.repositories.ClientRepository;
import org.popaqConnect.dtos.requests.RegisterRequest;
import org.popaqConnect.dtos.requests.SearchByDRopTitleRequest;
import org.popaqConnect.exceptions.InvalidDetailsException;
import org.popaqConnect.exceptions.UserExistException;
import org.popaqConnect.utils.Mapper;
import org.popaqConnect.utils.VerifyPassword;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImp implements ClientService{
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    JobService jobService;

    @Override
    public void register(RegisterRequest registerRequest) {
        if(userExist(registerRequest.getEmail()))throw new UserExistException("User exist");
        if(!VerifyPassword.verifyPassword(registerRequest.getPassword()))throw new InvalidDetailsException("Wrong password format");
        if(!VerifyPassword.verifyEmail(registerRequest.getEmail()))throw new InvalidDetailsException("Invalid email format");
        Client newClient = Mapper.mapClient(registerRequest);
        clientRepository.save(newClient);
    }
    @Override
    public List<Job> searchBYDropTitle(SearchByDRopTitleRequest search) {
        Client client = clientRepository.findByEmail(search.getEmail());
        List<Job> jobList = jobService.findByTitle(search.getTitle());
        clientRepository.save(client);
        return jobList;
    }
    private boolean userExist(String email){
        Client client = clientRepository.findByEmail(email);
        return client!=null;
    }


}
