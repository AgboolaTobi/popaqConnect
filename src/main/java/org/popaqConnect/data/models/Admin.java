package org.popaqConnect.data.models;

<<<<<<< HEAD
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
=======
import lombok.Data;
import org.springframework.data.annotation.Id;
>>>>>>> c27a60656f073947c54bf5ed215985e256b3c8dc
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Admin {
    @Id
<<<<<<< HEAD
    @GeneratedValue(strategy = GenerationType.IDENTITY)
=======
>>>>>>> c27a60656f073947c54bf5ed215985e256b3c8dc
    private String id;
    private String name;
}
