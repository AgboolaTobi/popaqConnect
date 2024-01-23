package org.popaqConnect.services;

import org.popaqConnect.data.models.Book;
import org.popaqConnect.data.models.ServiceProvider;
import org.popaqConnect.dtos.requests.BookRequest;
import org.popaqConnect.dtos.requests.EmailRequest;
import org.popaqConnect.utils.EmailFinalResponse;
import org.popaqConnect.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    EmailServices emailServices;
    @Autowired
    BookServices bookServices;
    @Override
    public void sendAcceptRequest(String bookingId, String  clientEmail) {
        Book book = bookServices.findABookRequest(bookingId,clientEmail);
        String subject = "Response on " + bookingId;
        String description = "Your booking request Has Been accepted, You can contact " +
                book.getServiceProviderEmail();
        EmailRequest emailRequest = Mapper.mapEmailRequest(clientEmail,description,subject);
        emailServices.send(emailRequest);

    }

    @Override
    public void sendClientBookingRequestEmail(BookRequest bookRequest, String bookingId) {
        String description = Mapper.bookServiceHeadingToServiceProvider(bookRequest.getServiceProviderEmail(),bookRequest.getDescription(),bookRequest.getTime());
        String subject = "Request For Your Service";
        EmailRequest emailRequest = Mapper.mapEmailRequest(bookRequest.getServiceProviderEmail(),description,subject);
        emailServices.send(emailRequest);
        subject ="Booking Identity Number";
        String message = "Booking identity number is " + bookingId;
        emailRequest = Mapper.mapEmailRequest(bookRequest.getServiceProviderEmail(),message,subject);
        emailServices.send(emailRequest);

    }
}
