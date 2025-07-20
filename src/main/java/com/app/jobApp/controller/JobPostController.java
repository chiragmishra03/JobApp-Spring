package com.app.jobApp.controller;

import com.app.jobApp.model.JobPost;
import com.app.jobApp.service.JobPostService;
import com.app.jobApp.utilities.response.structure.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3001")
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
        ApiResponse<List<JobPost>> response;
        log.info("Received request to fetch all jobs");
        try {
            List<JobPost> jobPostList = jobPostService.getAllJobs();
            response = new ApiResponse<>("Success", HttpStatus.OK.value(), jobPostList);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.warn("Jobs not found");
            log.error(e.getMessage());
            response = new ApiResponse<>("Error", HttpStatus.NOT_FOUND.value(), null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<JobPost>> getJobById(@PathVariable("id") Long id) {
        ApiResponse<JobPost> response;
        log.info("Recieved request for get job Id "+id);
        try {
            JobPost jobPost = jobPostService.getJobById(id);
            response = new ApiResponse<>("Job With id " + id + " Found", HttpStatus.OK.value(), jobPost);
            log.info("Send response for job Id "+id);
        } catch (Exception e) {
            log.warn("Job With id " + id + " Not Found");
            log.error(e.getMessage());
            response = new ApiResponse<>("Job Not Found", HttpStatus.NOT_FOUND.value(), null);
        }
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(response.getStatusCode()));
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<JobPost>> createJob(@RequestBody JobPost jobPost) {
        ApiResponse<JobPost> response;
        log.info("Received request to create a new job");

        try {
            JobPost savedJob = jobPostService.createJob(jobPost);
            response = new ApiResponse<>("Job Created Successfully", HttpStatus.CREATED.value(), savedJob);
            log.info("Job created with ID: " + savedJob.getId());
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Error creating job: " + e.getMessage());
            response = new ApiResponse<>("Failed to create job", HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




}
