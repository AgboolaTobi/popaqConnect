package org.popaqConnect.data.models;

import lombok.Data;

@Data
public class ServiceProvider extends User{
    private Long id;
    private int yearsOfExperience;
    private String bioData;
    private double chargePerHour;
    private boolean isAvailable=true;
    private Job job;
}
