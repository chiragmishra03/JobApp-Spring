package com.app.jobApp.service;

import com.app.jobApp.model.JobPost;

import java.util.List;

public interface JobPostService {
    List<JobPost> getAllJobs();
    JobPost getJobById(Long id);
    JobPost createJob(JobPost jobPost);
}
