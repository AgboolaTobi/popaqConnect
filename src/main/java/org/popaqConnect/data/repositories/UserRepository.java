package org.popaqConnect.data.repositories;

import org.popaqConnect.data.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
