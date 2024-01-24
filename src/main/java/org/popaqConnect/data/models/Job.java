package org.popaqConnect.data.models;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.popaqConnect.data.JobCategory;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String jobTitle;
    private JobCategory jobCategory;

}
