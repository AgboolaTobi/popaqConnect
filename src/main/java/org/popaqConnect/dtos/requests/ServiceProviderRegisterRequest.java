package org.popaqConnect.dtos.requests;

import lombok.Data;
import org.apache.tomcat.Jar;
import org.popaqConnect.data.models.Job;

@Data
public class ServiceProviderRegisterRequest {
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
    private Job job;


}
