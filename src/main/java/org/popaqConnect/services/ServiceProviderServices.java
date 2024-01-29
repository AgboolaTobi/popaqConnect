package org.popaqConnect.services;

import org.popaqConnect.data.models.ServiceProvider;
import org.popaqConnect.dtos.requests.*;

import java.util.Optional;

public interface ServiceProviderServices {
    void register(ServiceProviderRegisterRequest registerRequest);

    void login(LoginRequest loginRequest);

    Optional<ServiceProvider> findUser(String serviceProviderEmail);

    void acceptClientBookRequest(AcceptBookingRequest bookingRequest);

    void completeJobStatus(CompleteJobRequest completeJobRequest);

    void cancelClientBookRequest(CancelBookingRequest cancelBookingRequest);

    void updateDetails(UpdateProfileRequest updateProfileRequest);

    void logout(String email);
}
