package org.popaqConnect.data.models;
import lombok.Data;
<<<<<<< HEAD
=======
import org.springframework.data.annotation.Id;
>>>>>>> c27a60656f073947c54bf5ed215985e256b3c8dc
import org.springframework.data.mongodb.core.mapping.Document;



@Document
@Data
<<<<<<< HEAD
@Document

public class Trainee{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String age;
    private String address;
    private boolean isLoginStatus;
    private String password;
=======
public class Trainee{
    @Id
>>>>>>> c27a60656f073947c54bf5ed215985e256b3c8dc
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
