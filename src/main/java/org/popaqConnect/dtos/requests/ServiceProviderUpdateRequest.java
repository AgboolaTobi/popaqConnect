package org.popaqConnect.dtos.requests;

import lombok.Data;

@Data
public class ServiceProviderUpdateRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String address;
    private String password;
    private String age;
    private int yearsOfExperience;
    private String bioData;
    private double chargePerHour;
    private String category;
    private String jobTitle;
}
