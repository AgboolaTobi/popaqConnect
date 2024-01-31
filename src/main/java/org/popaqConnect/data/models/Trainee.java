package org.popaqConnect.data.models;
import lombok.Data;
import org.popaqConnect.data.CourseStatus;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



@Document
@Data
public class Trainee{
    @Id
    private String id;
    private String bioData;
    private String username;
    private String email;
    private String phoneNumber;
    private String address;
    private String password;
    private boolean isLoginStatus;
}
