package org.popaqConnect.dtos.requests;

import lombok.Data;

@Data
public class EmailRequest {
    private String reciepent;
    private String message;
    private String Subject;
}
