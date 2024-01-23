package org.popaqConnect.services;

import org.popaqConnect.data.models.Job;
import org.popaqConnect.data.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class JobServiceImpl implements  JobService{
    @Autowired
    JobRepository jobRepository;
    @Override
    public List<Job> findByTitle(String title) {
        List<Job> jobList1 = new ArrayList<>();
        List<Job> jobList = jobRepository.findAll();
        for (Job job : jobList){
            if (job.getJobTitle().equals(title)) {
                jobList1.add(job);
            }
        }
        return jobList1;
    }
}
