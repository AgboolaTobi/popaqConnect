package org.popaqConnect.services.Admin;

import org.popaqConnect.data.BookType;
import org.popaqConnect.data.CourseStatus;
import org.popaqConnect.data.models.Book;
import org.popaqConnect.data.models.CourseApplication;
import org.popaqConnect.dtos.requests.BookRequest;
import org.popaqConnect.dtos.requests.EmailRequest;
import org.popaqConnect.dtos.requests.TrainingRequest;
import org.popaqConnect.services.Booking.BookServices;
import org.popaqConnect.services.email.EmailServices;
import org.popaqConnect.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    EmailServices emailServices;
    @Autowired
    BookServices bookServices;

    @Override
    public void sendAcceptRequest(String bookingId, String clientEmail) {
        Book book = bookServices.findABookingRequest(bookingId, clientEmail);
        EmailRequest emailRequest;
        emailRequest = getEmailResponse(bookingId, clientEmail, book);
        emailServices.send(emailRequest);

    }

    private static EmailRequest getEmailResponse(String bookingId, String clientEmail, Book book) {
        EmailRequest emailRequest;
        String subject = "Response on " + bookingId;
        String description;
        if (book.getProjectStatus() == BookType.ACCEPTED) {
            description = "Your booking request Has Been accepted, You can contact " +
                    book.getServiceProviderEmail();
        } else {
            description = "Your booking request Has Been Rejected, You can contact " +
                    book.getServiceProviderEmail();
        }
        emailRequest = Mapper.mapEmailRequest(clientEmail, description, subject);
        return emailRequest;
    }

    @Override
    public void sendClientBookingRequestEmail(String firstName, BookRequest bookRequest, String bookingId) {
        String description = Mapper.bookServiceHeadingToServiceProvider(firstName, bookRequest.getDescription(), bookRequest.getTime(), bookingId);
        String subject = "Request For Your Service";
        EmailRequest emailRequest = Mapper.mapEmailRequest(bookRequest.getServiceProviderEmail(), description, subject);
        emailServices.send(emailRequest);
    }

    @Override
    public void sendTraineeApplicationRequest(String firstName, String courseCode, TrainingRequest request) {
        String description = Mapper.trainingApplicationEmail(firstName, courseCode, request);
        String subject = "New Training Application";
        EmailRequest emailRequest = Mapper.mapEmailRequest(request.getTrainerEmail(), description, subject);
        emailServices.send(emailRequest);
    }

    @Override
    public void sendTraineeCourseApplicationResponse(String courseCode, CourseApplication course) {
        EmailRequest emailRequest;
        String subject = "Outcome of Your Training Application with Course code " + courseCode;
        String description = Mapper.mapTraineeApplication(courseCode,course);
        emailRequest = Mapper.mapEmailRequest(course.getTraineeEmail(), description, subject);
        emailServices.send(emailRequest);

    }

    @Override
    public void sendCancelEmail(String email, String id) {
        EmailRequest emailRequest;
        String subject = "Update on " + id;
        String description = "This Service with " + id + " has been Cancel";
        emailRequest = Mapper.mapEmailRequest(email, description, subject);
        emailServices.send(emailRequest);
    }
}