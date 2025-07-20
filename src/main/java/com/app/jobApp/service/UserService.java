package com.app.jobApp.service;

import com.app.jobApp.dto.user.UserRequestDto;
import com.app.jobApp.dto.user.UserResponseDto;
import com.app.jobApp.model.User;

import java.util.List;

public interface UserService {
    UserResponseDto createUser(UserRequestDto request);
    List<UserResponseDto> getAllUsers();
}
