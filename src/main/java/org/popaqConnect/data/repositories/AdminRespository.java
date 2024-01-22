package org.popaqConnect.data.repositories;

import org.popaqConnect.data.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRespository extends JpaRepository<Admin, Long> {
}
