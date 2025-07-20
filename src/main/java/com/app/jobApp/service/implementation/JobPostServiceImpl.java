package com.app.jobApp.service.implementation;

import com.app.jobApp.model.JobPost;
import com.app.jobApp.repo.JobPostRepository;
import com.app.jobApp.service.JobPostService;
import com.app.jobApp.utilities.exception.GlobalException;
import com.app.jobApp.utilities.exception.jobnotfound.JobNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobPostServiceImpl implements JobPostService {

    private final JobPostRepository jobPostRepository;

    public JobPostServiceImpl(JobPostRepository jobPostRepository) {
        this.jobPostRepository = jobPostRepository;
    }

    @Override
    public List<JobPost> getAllJobs() {
        List<JobPost> jobPostList;
        try {
            jobPostList = jobPostRepository.findAll();
            if (jobPostList.isEmpty()) {
                throw new GlobalException();
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return jobPostList;
    }

    @Override
    public JobPost getJobById(Long id) {
        return jobPostRepository.findById(id).orElseThrow(() -> new JobNotFoundException(id));
    }

    @Override
    public JobPost createJob(JobPost jobPost) {
        return jobPostRepository.save(jobPost);
    }


}
