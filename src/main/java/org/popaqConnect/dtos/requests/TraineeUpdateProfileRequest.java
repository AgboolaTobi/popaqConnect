package org.popaqConnect.dtos.requests;

import lombok.Data;

@Data
public class TraineeUpdateProfileRequest {
    private String username;
    private String email;
    private String updatedEmail;
    private String phoneNumber;
    private String address;
    private String password;
    private String bioData;
}
