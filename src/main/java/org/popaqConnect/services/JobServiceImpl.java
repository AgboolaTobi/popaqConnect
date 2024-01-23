package org.popaqConnect.services;

import org.popaqConnect.data.JobCategory;
import org.popaqConnect.data.models.Job;
import org.popaqConnect.data.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobServiceImpl implements JobService{
    @Autowired
    JobRepository jobRepository;
    @Override
    public Job save(String jobCategory,String jobTitle) {
        Job job = setCategory(jobCategory);
        job.setJobTitle(jobTitle);
        jobRepository.save(job);
        return job;


    }
    private Job setCategory(String category){
        Job job = new Job();
        for(JobCategory category1: JobCategory.values()){
            if(category1.name().equalsIgnoreCase(category)){
                job.setJobCategory(category1);
            }
            job.setJobCategory(JobCategory.valueOf(category));
        }
        return job;
    }
}
