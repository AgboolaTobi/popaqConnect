package org.popaqConnect.data.models;


import lombok.Data;
import org.popaqConnect.data.JobCategory;
<<<<<<< HEAD
=======
import org.springframework.data.annotation.Id;
>>>>>>> c27a60656f073947c54bf5ed215985e256b3c8dc
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Job {
    @Id
    private String id;
    private String jobTitle;
    private JobCategory jobCategory;

}
