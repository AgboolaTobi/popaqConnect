package org.popaqConnect.dtos.requests;

import lombok.Data;

@Data
public class LoginRequest {
    public String clientEmail;
    private String password;
}
