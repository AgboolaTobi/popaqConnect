package org.popaqConnect.services.Admin;

import org.popaqConnect.dtos.requests.BookRequest;
import org.popaqConnect.dtos.requests.TrainingRequest;
import org.popaqConnect.dtos.response.ApplyForTrainingResponse;

public interface AdminService {
    void sendAcceptRequest(String bookingId, String clientEmail);
    void sendClientBookingRequestEmail(String firstName,BookRequest bookRequest,String bookingId);

    void sendTraineeApplicationRequest(String firstName, String courseCode, TrainingRequest request);
}
