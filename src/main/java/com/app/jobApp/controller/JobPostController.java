package com.app.jobApp.controller;

import com.app.jobApp.model.JobPost;
import com.app.jobApp.service.JobPostService;
import com.app.jobApp.utilities.response.structure.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/jobs")
public class JobPostController {

    private final JobPostService jobPostService;
    private static final Logger log = LoggerFactory.getLogger(JobPostController.class);

    public JobPostController(JobPostService jobPostService) {
        this.jobPostService = jobPostService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<JobPost>>> getAllJobs() {
        log.info("Received request to fetch all jobs");
        try {
            List<JobPost> jobPostList = jobPostService.getAllJobs();
            ApiResponse<List<JobPost>> response = new ApiResponse("Success", HttpStatus.OK.value(), jobPostList);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.warn("Jobs not found");
            log.error(e.getMessage());
            ApiResponse<List<JobPost>> response = new ApiResponse("Error fetching jobs", HttpStatus.NOT_FOUND.value(), null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<JobPost>> getJobById(@PathVariable("id") Long id) {
        log.info("Received request to fetch job with ID: {}", id);
        try {
            JobPost jobPost = jobPostService.getJobById(id);
            ApiResponse<JobPost> response = new ApiResponse<>("Job found", HttpStatus.OK.value(), jobPost);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.warn("Job with ID {} not found", id);
            log.error(e.getMessage());
            ApiResponse<JobPost> response = new ApiResponse<>("Job Not Found", HttpStatus.NOT_FOUND.value(), null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<JobPost>> createJob(@RequestBody JobPost jobPost) {
        log.info("Received request to create a new job");

        try {
            JobPost savedJob = jobPostService.createJob(jobPost);
            ApiResponse<JobPost> response = new ApiResponse<>("Job Created Successfully", HttpStatus.CREATED.value(), savedJob);
            log.info("Job created with ID: {}", savedJob.getId());
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            log.error("Error creating job: {}", e.getMessage());
            ApiResponse<JobPost> response = new ApiResponse<>("Failed to create job", HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
