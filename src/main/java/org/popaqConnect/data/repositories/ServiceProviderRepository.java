package org.popaqConnect.data.repositories;

import org.popaqConnect.data.models.ServiceProvider;
import org.springframework.data.mongodb.repository.MongoRepository;
<<<<<<< HEAD

public interface ServiceProviderRepository extends MongoRepository<ServiceProvider,String> {
=======
import java.util.Optional;

public interface ServiceProviderRepository extends MongoRepository<ServiceProvider,String> {
    Optional<ServiceProvider> findByEmail(String serviceProviderEmail);

>>>>>>> c27a60656f073947c54bf5ed215985e256b3c8dc
}
