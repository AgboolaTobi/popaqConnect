package org.popaqConnect.data.repositories;

import org.popaqConnect.data.models.CourseApplication;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CourseApplicationRepository extends MongoRepository<CourseApplication,String> {

}
