package org.popaqConnect.data.repositories;

import org.popaqConnect.data.models.Admin;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends MongoRepository<Admin, String> {

    boolean existsByPassword(String password);

    Admin findByEmailAndPassword(String email, String password);

    Admin findByEmail(String email);

}
