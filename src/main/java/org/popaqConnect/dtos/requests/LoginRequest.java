package org.popaqConnect.dtos.requests;

import lombok.Data;

@Data
public class LoginRequest {

    public String email;

    private String password;
}
