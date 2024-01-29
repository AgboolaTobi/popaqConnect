package org.popaqConnect.data.models;

import lombok.Data;
import org.popaqConnect.data.CourseStatus;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class CourseApplication {
    @Id
    private String courseId;
    private String aboutYou;
    private String expectedStartDate;
    private String dueDate;
    private String trainerEmail;
    private String traineeEmail;
    private CourseStatus courseStatus = CourseStatus.NOVICE;


}
