package org.popaqConnect.services.Trainee;


import org.popaqConnect.data.models.CourseApplication;
import org.popaqConnect.data.models.ServiceProvider;
import org.popaqConnect.dtos.requests.*;
import org.popaqConnect.dtos.response.ApplyForTrainingResponse;

import java.util.List;

public interface TraineeService  {
    void register(RegisterRequest registerRequest);
    void login(LoginRequest loginRequest);

    ApplyForTrainingResponse applyForTraining(TrainingRequest traineeApplication);

    List<ServiceProvider> searchForTrainers(String email);

    void cancelCourseApplication(CancelCourseRequest cancelCourseRequest);

    CourseApplication viewCourseApplication(ViewCourseApplicationRequest viewCourseApplication);

    void updateProfile(TraineeUpdateProfileRequest updateDetailRequest);

    void logout(String email);

}
