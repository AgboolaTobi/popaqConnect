package org.popaqConnect.services.job;
import org.popaqConnect.data.JobCategory;
import org.popaqConnect.data.models.Job;
import org.popaqConnect.data.repositories.JobRepository;
import org.popaqConnect.services.job.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class JobServiceImpl implements JobService {
    @Autowired
    JobRepository jobRepository;

    @Override
    public List<Job> findByTitle(String title) {
        List<Job> jobList1 = new ArrayList<>();
        List<Job> jobList = jobRepository.findAll();
        for (Job job : jobList) {
            if (job.getJobTitle().equals(title)) {
                jobList1.add(job);
            }
        }
        return jobList1;
    }
    @Override
    public Job save(String jobCategory, String jobTitle) {
        Job job = setCategory(jobCategory);
        job.setJobTitle(jobTitle);
        jobRepository.save(job);
        return job;

    }
    private Job setCategory(String category) {
        Job job = new Job();
        for (JobCategory category1 : JobCategory.values()) {
            if (category1.name().equalsIgnoreCase(category)) {
                job.setJobCategory(category1);
            }
            job.setJobCategory(JobCategory.valueOf(category));
        }
        return job;
    }
}