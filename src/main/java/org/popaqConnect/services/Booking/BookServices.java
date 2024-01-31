package org.popaqConnect.services.Booking;

import org.popaqConnect.data.models.Book;
import org.popaqConnect.dtos.requests.AcceptBookingRequest;
import org.popaqConnect.dtos.requests.BookRequest;
import org.popaqConnect.dtos.requests.CancelBookingRequest;
import org.popaqConnect.dtos.requests.CompleteJobRequest;

import java.util.List;

public interface BookServices {
    String save(BookRequest bookRequest);

    List<Book> findAllBookingRequest(String userEmail);

    Book findABookingRequest(String bookID, String userEmail);

    Book setBookType(String serviceProviderId, AcceptBookingRequest bookingRequest);

    void completeJobStatus(CompleteJobRequest completeJobRequest);
    void cancelBookRequest(String bookId,String email);
}
