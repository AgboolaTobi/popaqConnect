package org.popaqConnect.services;

import org.popaqConnect.data.models.ServiceProvider;
import org.popaqConnect.dtos.requests.AcceptBookingRequest;
import org.popaqConnect.dtos.requests.LoginRequest;
import org.popaqConnect.dtos.requests.RegisterRequest;
import org.popaqConnect.dtos.requests.ServiceProviderRegisterRequest;

import java.util.Optional;

public interface ServiceProviderServices {
    void register(ServiceProviderRegisterRequest registerRequest);

    void login(LoginRequest loginRequest);

    Optional<ServiceProvider> findUser(String serviceProviderEmail);

    void acceptClientBookRequest(AcceptBookingRequest bookingRequest);
}
