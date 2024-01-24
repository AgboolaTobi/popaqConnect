package org.popaqConnect.services;


import org.popaqConnect.dtos.requests.LoginRequest;
import org.popaqConnect.dtos.requests.RegisterRequest;

public interface TraineeService  {
    void register(RegisterRequest registerRequest);
    void login(LoginRequest loginRequest);
}
