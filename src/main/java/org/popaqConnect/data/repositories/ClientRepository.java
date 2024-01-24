package org.popaqConnect.data.repositories;

import org.popaqConnect.data.models.Client;
<<<<<<< HEAD

import org.springframework.data.mongodb.repository.MongoRepository;

=======
import org.springframework.data.mongodb.repository.MongoRepository;

>>>>>>> c27a60656f073947c54bf5ed215985e256b3c8dc
public interface ClientRepository extends MongoRepository<Client,String> {
    Client findByEmail(String email);
    }
