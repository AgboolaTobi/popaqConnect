package org.popaqConnect.services;

import org.popaqConnect.data.models.ServiceProvider;
import org.popaqConnect.dtos.requests.AcceptBookingRequest;
import org.popaqConnect.dtos.requests.RegisterRequest;

import java.util.Optional;

public interface ServiceProviderServices {
    Optional<ServiceProvider> findUser(String serviceProviderEmail);

    void register(RegisterRequest registerRequest);

    void acceptClientBookRequest(AcceptBookingRequest bookingRequest);
}
