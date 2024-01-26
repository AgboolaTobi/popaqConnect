package org.popaqConnect.data.models;
import lombok.Data;
<<<<<<< HEAD
=======
import org.springframework.data.annotation.Id;
>>>>>>> c27a60656f073947c54bf5ed215985e256b3c8dc
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document
<<<<<<< HEAD

public class ServiceProvider{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
=======
public class ServiceProvider {
    @Id
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
=======
>>>>>>> c27a60656f073947c54bf5ed215985e256b3c8dc
    private String password;
    private int yearsOfExperience;
    private String bioData;
    private double chargePerHour;
    private boolean isAvailable=true;
    private boolean isLoginStatus;
    private Job job;
    private List<Trainee> trainees;
    private boolean availableForTraining;
}
