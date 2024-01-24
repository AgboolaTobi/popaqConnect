package org.popaqConnect.dtos.requests;

import lombok.Data;

@Data
public class ClientLoginRequest {
    private String email;
    private String password;
}
