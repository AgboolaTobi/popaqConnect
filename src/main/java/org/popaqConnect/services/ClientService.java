package org.popaqConnect.services;

import org.popaqConnect.dtos.requests.RegisterRequest;

public interface ClientService {

    void register(RegisterRequest registerRequest);

    void login(org.popaqConnect.dtos.requests.LoginRequest loginRequest);
}