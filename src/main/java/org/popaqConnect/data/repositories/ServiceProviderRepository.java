package org.popaqConnect.data.repositories;

import org.popaqConnect.data.models.ServiceProvider;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ServiceProviderRepository extends MongoRepository<ServiceProvider,String> {
}
