package org.popaqConnect.data.repositories;

import org.popaqConnect.data.models.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job,Long> {

}
