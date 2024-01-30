package org.popaqConnect.dtos.requests;

import lombok.Data;

@Data
public class ClientUpdateRequest {
    private String userName;
    private String email;
    private String phoneNumber;
    private String address;
    private String password;
    private String age;
}
