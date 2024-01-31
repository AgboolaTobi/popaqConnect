package org.popaqConnect.data.models;


import lombok.Data;
import org.popaqConnect.data.JobCategory;

import org.springframework.data.annotation.Id;

import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Job {
    @Id
    private String id;
    private String jobTitle;
    private JobCategory jobCategory;

}
