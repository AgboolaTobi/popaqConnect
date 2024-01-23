package org.popaqConnect.data.models;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class ServiceProvider {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String age;
    private String address;
    private boolean isLoginStatus;
    private String password;
    private int yearsOfExperience;
    private String bioData;
    private double chargePerHour;
    private boolean isAvailable=true;
    private Job job;
}
