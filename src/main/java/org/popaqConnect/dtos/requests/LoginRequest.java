package org.popaqConnect.dtos.requests;

import lombok.Data;

@Data
public class LoginRequest {

    private String email;
    public String clientEmail;
    private String password;
}
