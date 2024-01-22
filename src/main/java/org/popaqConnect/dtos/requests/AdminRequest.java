package org.popaqConnect.dtos.requests;

import lombok.Data;

@Data
public class AdminRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String password;
}
