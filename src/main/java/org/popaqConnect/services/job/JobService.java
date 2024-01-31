package org.popaqConnect.services.job;


import org.popaqConnect.data.models.Job;
import org.popaqConnect.data.models.ServiceProvider;

import java.util.List;

public interface JobService {
    Job save(String category, String title);

}