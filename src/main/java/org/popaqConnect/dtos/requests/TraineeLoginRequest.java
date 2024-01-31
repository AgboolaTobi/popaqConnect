package org.popaqConnect.dtos.requests;

import lombok.Data;

@Data
public class TraineeLoginRequest {
    private String email;
    private String password;
}
