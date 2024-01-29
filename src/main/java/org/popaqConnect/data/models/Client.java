package org.popaqConnect.data.models;

import lombok.Data;
<<<<<<< HEAD
=======
import org.springframework.data.annotation.Id;
>>>>>>> c27a60656f073947c54bf5ed215985e256b3c8dc
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document

<<<<<<< HEAD
public class Client{
=======
>>>>>>> c27a60656f073947c54bf5ed215985e256b3c8dc

public class Client {
    @Id
<<<<<<< HEAD
    @GeneratedValue(strategy = GenerationType.IDENTITY)
=======
>>>>>>> c27a60656f073947c54bf5ed215985e256b3c8dc
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String age;
    private String address;
<<<<<<< HEAD
    private boolean isLoginStatus;
    private String password;
=======
    private String password;
    private boolean isLoginStatus;

>>>>>>> c27a60656f073947c54bf5ed215985e256b3c8dc
}
