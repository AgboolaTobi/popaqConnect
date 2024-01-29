package org.popaqConnect.dtos.requests;

import lombok.Data;

@Data
public class ViewCourseApplicationRequest {
    private String traineeEmail;
    private String courseCode;

}
