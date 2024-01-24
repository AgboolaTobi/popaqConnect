package org.popaqConnect.dtos.requests;

import java.time.LocalDateTime;

public class BookServiceRequest {
    private String serviceProviderId;
    private String clientId;
    private String description;
    private boolean isAccepted;
    private final LocalDateTime dateTime = LocalDateTime.now();
}
