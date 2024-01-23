package org.popaqConnect.services;

import org.popaqConnect.dtos.requests.BookRequest;

public interface AdminService {
    void sendAcceptRequest(String bookingId, String clientEmail);
    void sendClientBookingRequestEmail(BookRequest bookRequest,String bookingId);
}
