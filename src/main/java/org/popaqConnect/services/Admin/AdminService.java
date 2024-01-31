package org.popaqConnect.services.Admin;

import org.popaqConnect.data.models.CourseApplication;
import org.popaqConnect.dtos.requests.BookRequest;
import org.popaqConnect.dtos.requests.TrainingRequest;

public interface AdminService {
    void sendAcceptRequest(String bookingId, String clientEmail);
    void sendClientBookingRequestEmail(String firstName,BookRequest bookRequest,String bookingId);

    void sendTraineeApplicationRequest(String firstName, String courseCode, TrainingRequest request);

    void sendTraineeCourseApplicationResponse(String courseCode, CourseApplication course);

    void sendCancelEmail(String serviceProviderEmail, String bookingId);
}
