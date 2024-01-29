package org.popaqConnect.dtos.requests;

import lombok.Data;

@Data
public class CancelBookingRequest {
    private String bookId;
    private String email;
    private String jobStatus;
}
