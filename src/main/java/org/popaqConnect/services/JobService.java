package org.popaqConnect.services;

import org.popaqConnect.data.models.Job;

import java.util.List;

public interface JobService {
    List<Job> findByTitle(String title);

}
