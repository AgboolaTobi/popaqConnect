package org.popaqConnect.data.models;

import lombok.Data;

import org.springframework.data.annotation.Id;

import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document

public class Client {
    @Id
    private String id;
    private String userName;

    private String email;
    private String phoneNumber;

    private String address;

    private boolean isLoginStatus;
    private String password;
    private boolean isLocked;
}
