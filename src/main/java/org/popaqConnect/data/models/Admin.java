package org.popaqConnect.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Admin {
   @Id
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String password;
    private boolean isLoggedIn;


}
