package org.popaqConnect.services.serviceProvider;

import org.popaqConnect.data.models.Book;
import org.popaqConnect.data.models.CourseApplication;
import org.popaqConnect.data.models.ServiceProvider;
import org.popaqConnect.dtos.requests.*;

import java.util.List;
import java.util.Optional;

public interface ServiceProviderServices {
    void register(ServiceProviderRegisterRequest registerRequest);

    void login(LoginRequest loginRequest);

    Optional<ServiceProvider> findUser(String serviceProviderEmail);

    void acceptClientBookRequest(AcceptBookingRequest bookingRequest);

    List<ServiceProvider> findAllServiceProviderAvailableForTraining();

    CourseApplication viewACourseApplication(ViewTraineeCourseRequest traineeRequest);

    void updateOnTrainee(UpdateOnCourseApplicationRequest updateOnCourseApplicationRequest);

    List<Book> findAllBookingHistory(String mail);

    Book viewABookingHistory(ViewABookingRequest viewABookingRequest);

    void removeUser(String traineeEmail, String trainerEmail);

    void cancleRequest(CancelServiceProviderRequest cancelRequest);
}
