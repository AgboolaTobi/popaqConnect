package org.popaqConnect.utils;

import org.popaqConnect.data.models.Client;
import org.popaqConnect.dtos.requests.RegisterRequest;

public class Mapper {

    public static Client mapClient(RegisterRequest registerRequest){
        Client client = new Client();
        client.setFirstName(registerRequest.getFirstName());
        client.setLastName(registerRequest.getLastName());
        client.setEmail(registerRequest.getEmail());
        client.setAddress(registerRequest.getAddress());
        client.setPhoneNumber(registerRequest.getPhoneNumber());
        client.setAge(registerRequest.getAge());
        return client;
    }
}
