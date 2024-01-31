package org.popaqConnect.dtos.requests;

import lombok.Data;

@Data
public class FindTraineeByEmailRequest {

    private String serviceProviderEmail;
    private String traineeEmail;
}
