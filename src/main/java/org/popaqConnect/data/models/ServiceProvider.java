package org.popaqConnect.data.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@DiscriminatorValue("ServiceProvider")
public class ServiceProvider extends User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int yearsOfExperience;
    private String bioData;
    private double chargePerHour;
    private boolean isAvailable=true;
    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Job job;
}
