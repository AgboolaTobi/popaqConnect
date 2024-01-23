package org.popaqConnect.services;

import org.popaqConnect.data.models.Job;
import org.springframework.stereotype.Service;
public interface JobService {
    Job save(String category, String title);
}
