package org.popaqConnect.dtos.requests;

import lombok.Data;

@Data
public class ResponseToTrainingRequest {
    private String email;
    private String traineeEmail;
    private String courseCode;
    private String response;

}
