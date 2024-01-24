package org.popaqConnect.data.models;


import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document

public class Client{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String age;
    private String address;
    private boolean isLoginStatus;
    private String password;
}
