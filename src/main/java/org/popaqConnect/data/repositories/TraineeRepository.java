package org.popaqConnect.data.repositories;

import org.popaqConnect.data.models.Trainee;
import org.springframework.data.mongodb.repository.MongoRepository;
<<<<<<< HEAD

public interface TraineeRepository extends MongoRepository<Trainee,String> {
=======

public interface TraineeRepository extends MongoRepository<Trainee,String> {
    Trainee findByEmail(String email);

>>>>>>> c27a60656f073947c54bf5ed215985e256b3c8dc
}
