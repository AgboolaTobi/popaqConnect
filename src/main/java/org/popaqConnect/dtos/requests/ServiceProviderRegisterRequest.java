package org.popaqConnect.dtos.requests;

import lombok.Data;
import org.apache.tomcat.Jar;
import org.popaqConnect.data.models.Job;

@Data
public class ServiceProviderRegisterRequest {
    private String userName;
    private String email;
    private String phoneNumber;
    private String address;
    private String password;
    private int yearsOfExperience;
    private String bioData;
    private double chargePerHour;
    private String category;
    private String jobTitle;


}
