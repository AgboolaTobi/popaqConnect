package org.popaqConnect.services;

import org.popaqConnect.data.models.Book;
import org.popaqConnect.data.models.ServiceProvider;
import org.popaqConnect.dtos.requests.AcceptBookingRequest;
import org.popaqConnect.dtos.requests.BookRequest;
import org.popaqConnect.dtos.requests.CancelBookingRequest;
import org.popaqConnect.dtos.requests.CompleteJobRequest;

import java.util.List;
import java.util.Optional;

public interface BookServices {
    String save(BookRequest bookRequest);

    List<Book> findUserBookRequest(String userEmail);

    Book findABookRequest(String bookID, String userEmail);

    void setBookType(String serviceProviderId, AcceptBookingRequest bookingRequest);

    void completeJobStatus(CompleteJobRequest completeJobRequest);
    void cancelBookRequest(CancelBookingRequest cancelBookingRequest);
}
