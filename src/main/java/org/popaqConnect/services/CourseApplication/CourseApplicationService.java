package org.popaqConnect.services.CourseApplication;

import org.popaqConnect.data.models.CourseApplication;
import org.popaqConnect.dtos.requests.TrainingRequest;
import org.popaqConnect.dtos.requests.UpdateOnCourseApplicationRequest;
import org.popaqConnect.dtos.response.ApplyForTrainingResponse;

import java.util.List;

public interface CourseApplicationService {
    ApplyForTrainingResponse setupCourseApplication(TrainingRequest request);

    CourseApplication findCourse(String courseCode, String email);

    List<CourseApplication> findAllCourse(String email);

    void updateCourseApplication(UpdateOnCourseApplicationRequest updateCourseRequest);

    void cancelCourse(String courseCode, String traineeEmail);
}
