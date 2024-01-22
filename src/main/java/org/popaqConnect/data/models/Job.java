package org.popaqConnect.data.models;


import lombok.Data;
import org.popaqConnect.data.JobCategory;

@Data
public class Job {
    private String jobTitle;
    private JobCategory jobCategory;

}
