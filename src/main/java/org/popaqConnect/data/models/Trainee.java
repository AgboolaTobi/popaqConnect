package org.popaqConnect.data.models;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



@Document
@Data
public class Trainee{
    @Id
    private String bioData;
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String age;
    private String address;
    private String password;
    private boolean isLocked;
}
