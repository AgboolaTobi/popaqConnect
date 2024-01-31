package org.popaqConnect.services.serviceProvider;

import org.popaqConnect.data.models.Book;
import org.popaqConnect.data.models.CourseApplication;
import org.popaqConnect.data.models.ServiceProvider;
import org.popaqConnect.data.models.Trainee;
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
    void cancleTrainingRequest(CancelServiceProviderRequest cancelRequest);

    void completeJobStatus(CompleteJobRequest completeJobRequest);

    void cancelClientBookRequest(CancelBookingRequest cancelBookingRequest);

    void logout(String email);

    void updateDetails(UpdateProfileRequest updateProfileRequest);

    void responseToTrainingRequest(ResponseToTrainingRequest response);

    void saveTrainees(Trainee foundTrainee, String trainerEmail);

    void save(String serviceProviderEmail);


    void deleteAccount(String email);

}
