package com.app.jobApp.utilities.exception.jobnotfound;

public class JobNotFoundException extends RuntimeException {
    public JobNotFoundException(Long id) {
        super("Job with ID " + id + " not found");
    }
}
