package org.popaqConnect.dtos.requests;

import lombok.Data;

@Data
public class JobRequest {

    private String jobTitle;
    private JobCategory jobCategory;
}
