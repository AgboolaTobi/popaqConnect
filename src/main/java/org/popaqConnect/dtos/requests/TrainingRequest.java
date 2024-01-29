package org.popaqConnect.dtos.requests;

import lombok.Data;

@Data
public class TrainingRequest {
    private String traineeEmail;
    private String trainerEmail;
    private String startDate;
    private String endDate;
    private String aboutYou;
    

}
