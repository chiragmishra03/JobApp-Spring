package com.app.jobApp.controller;

import com.app.jobApp.dto.user.UserRequestDto;
import com.app.jobApp.dto.user.UserResponseDto;
import com.app.jobApp.service.JobPostService;
import com.app.jobApp.service.UserService;
import com.app.jobApp.utilities.response.structure.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Create User
    @PostMapping("/create")
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserRequestDto userRequestDto) {
        ApiResponse<UserResponseDto> response;
        log.info("Received request to create user with email: {}", userRequestDto.getEmail());
        try {
            UserResponseDto createdUser = userService.createUser(userRequestDto);
            response = new ApiResponse<>("User created successfully", HttpStatus.CREATED.value(), createdUser);
            return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
        } catch (Exception e) {
            log.warn("User creation failed");
            log.error("Error: {}", e.getMessage());
            response = new ApiResponse<>("User creation failed", HttpStatus.BAD_REQUEST.value(), null);
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

}
