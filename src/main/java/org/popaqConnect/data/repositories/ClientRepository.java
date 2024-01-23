package org.popaqConnect.data.repositories;

import org.popaqConnect.data.models.Client;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClientRepository extends MongoRepository<Client,String> {
    Client findByEmail(String email);
    }
