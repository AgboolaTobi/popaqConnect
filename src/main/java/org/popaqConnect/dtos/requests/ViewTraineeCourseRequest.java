package org.popaqConnect.dtos.requests;

import lombok.Data;

@Data
public class ViewTraineeCourseRequest {
    private String TrainerEmail;
    private String courseCode;
}
