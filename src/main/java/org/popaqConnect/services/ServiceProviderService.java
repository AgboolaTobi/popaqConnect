package org.popaqConnect.services;

import org.popaqConnect.dtos.requests.LoginRequest;
import org.popaqConnect.dtos.requests.ServiceProviderRegisterRequest;

public interface ServiceProviderService {

    void register(ServiceProviderRegisterRequest registerRequest);

    void login(LoginRequest loginRequest);
}
