package org.popaqConnect.dtos.requests;

import lombok.Data;

@Data
public class TraineeRegisterRequest {

    private String UserName;
    private String email;
    private String phoneNumber;
    private String address;
    private String password;
}
