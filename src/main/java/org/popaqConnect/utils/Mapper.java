package org.popaqConnect.utils;

import org.popaqConnect.data.models.Client;
import org.popaqConnect.data.models.Trainee;
import org.popaqConnect.dtos.requests.RegisterRequest;

public class Mapper {

    public static Client mapClient(RegisterRequest registerRequest){
        Client client = new Client();
        client.setFirstName(registerRequest.getFirstName());
        client.setLastName(registerRequest.getLastName());
        client.setEmail(registerRequest.getEmail());
        client.setPassword(registerRequest.getPassword());
        client.setAddress(registerRequest.getAddress());
        client.setPhoneNumber(registerRequest.getPhoneNumber());
        client.setAge(registerRequest.getAge());
        return client;
    }
    public static Trainee mapTrainee(RegisterRequest registerRequest){
        Trainee trainee = new Trainee();
        trainee.setFirstName(registerRequest.getFirstName());
        trainee.setLastName(registerRequest.getLastName());
        trainee.setAge(registerRequest.getAge());
        trainee.setPhoneNumber(registerRequest.getPhoneNumber());
        trainee.setAddress(registerRequest.getAddress());
        trainee.setEmail(registerRequest.getEmail());
        trainee.setPassword(registerRequest.getPassword());
        return trainee;
    }
}
