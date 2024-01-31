package org.popaqConnect.data.models;
import lombok.Data;

import org.springframework.data.annotation.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document


public class ServiceProvider {
    @Id

    private String id;
    private String userName;
    private String email;
    private String phoneNumber;
    private String address;

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
