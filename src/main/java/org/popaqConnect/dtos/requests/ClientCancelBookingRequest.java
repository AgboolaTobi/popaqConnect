package org.popaqConnect.dtos.requests;

import lombok.Data;

@Data
public class ClientCancelBookingRequest {
    private String clientEmail;
    private String serviceProviderEmail;
    private String bookingId;
}
