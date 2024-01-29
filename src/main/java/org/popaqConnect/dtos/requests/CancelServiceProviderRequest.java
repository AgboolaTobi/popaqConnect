package org.popaqConnect.dtos.requests;

import lombok.Data;

@Data
public class CancelServiceProviderRequest {
    private String serviceProviderEmail;
    private String id;
    private  String email;
}
