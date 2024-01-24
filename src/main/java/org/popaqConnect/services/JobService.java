package org.popaqConnect.services;


import org.popaqConnect.data.models.Job;

import java.util.List;

public interface JobService {
    Job save(String category, String title);

    List<Job> findByTitle(String title);
}