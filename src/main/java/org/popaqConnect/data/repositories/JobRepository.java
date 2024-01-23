package org.popaqConnect.data.repositories;

import org.popaqConnect.data.models.Job;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface JobRepository extends MongoRepository<Job,String> {
}
