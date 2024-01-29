package org.popaqConnect.dtos.requests;

import lombok.Data;

@Data
public class ViewABookingRequest {
    private String email;
    private String bookingId;

}
