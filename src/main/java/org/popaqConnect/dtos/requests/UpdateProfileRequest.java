package org.popaqConnect.dtos.requests;

import lombok.Data;
import org.popaqConnect.data.models.Job;

@Data
public class UpdateProfileRequest {
    private String username;
    private String previousEmail;
    private String updatedEmail;
    private String phoneNumber;
    private String address;
    private String password;
    private double chargePerHour;
    private Job job;
    private String bioData;
    private boolean availableForTraining;
}
