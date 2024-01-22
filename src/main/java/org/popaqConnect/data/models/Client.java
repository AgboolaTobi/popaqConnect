package org.popaqConnect.data.models;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@DiscriminatorValue("Client")

public class Client extends User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
