package org.popaqConnect.dtos.requests;

import lombok.Data;

@Data
public class AdminRequest {
    public String id;
    public String email;
    private String firstName;
    private String lastName;
    private String phone;
    private String password;
    private boolean isLocked;
}
