package org.popaqConnect.dtos.requests;

import lombok.Data;

@Data
public class CancelCourseRequest {
    private String traineeEmail;
    private String trainerEmail;
    private String courseCode;
}
