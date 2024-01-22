package org.popaqConnect.data.repositories;

import org.popaqConnect.data.models.ServiceProvider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceProviderRepository extends JpaRepository<ServiceProvider,Long> {
}
