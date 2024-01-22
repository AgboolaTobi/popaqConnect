package org.popaqConnect.data.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@DiscriminatorValue("Trainee")
public class Trainee extends User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String bioData;
}
