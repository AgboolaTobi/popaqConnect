package org.popaqConnect.dtos.requests;

import lombok.Data;

@Data
public class UpdateOnCourseApplicationRequest {
    private String courseCode;
    private String traineeEmail;
    private String trainerEmail;
    private String updateDueDate;
    private String updateCourseStatus;
}
