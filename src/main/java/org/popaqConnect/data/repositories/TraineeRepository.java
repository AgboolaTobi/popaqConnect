package org.popaqConnect.data.repositories;

import org.popaqConnect.data.models.Trainee;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TraineeRepository extends MongoRepository<Trainee,String> {
}
