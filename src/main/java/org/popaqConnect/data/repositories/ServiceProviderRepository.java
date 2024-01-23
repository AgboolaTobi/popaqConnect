package org.popaqConnect.data.repositories;

import org.popaqConnect.data.models.ServiceProvider;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ServiceProviderRepository extends MongoRepository<ServiceProvider,String> {
    Optional<ServiceProvider> findByEmail(String serviceProviderEmail);
}
