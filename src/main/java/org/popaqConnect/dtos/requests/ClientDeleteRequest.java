package org.popaqConnect.dtos.requests;

import lombok.Data;

@Data
public class ClientDeleteRequest {
    private String email;
    private String password;
}
