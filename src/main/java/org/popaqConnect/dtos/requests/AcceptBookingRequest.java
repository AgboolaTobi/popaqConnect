package org.popaqConnect.dtos.requests;

import lombok.Data;

@Data
public class AcceptBookingRequest {
    private String response;
    private String email;
    private String id;
}
