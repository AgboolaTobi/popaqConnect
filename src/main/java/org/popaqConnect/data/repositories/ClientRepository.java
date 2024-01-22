package org.popaqConnect.data.repositories;

import org.popaqConnect.data.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client,Long> {
}
