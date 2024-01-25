package org.popaqConnect.data.repositories;

import org.popaqConnect.data.models.Job;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JobRepository extends MongoRepository<Job,Long> {

}
