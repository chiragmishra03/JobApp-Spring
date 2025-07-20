package com.app.jobApp.service.implementation;

import com.app.jobApp.dto.user.UserRequestDto;
import com.app.jobApp.dto.user.UserResponseDto;
import com.app.jobApp.model.User;
import com.app.jobApp.repo.UserRepository;
import com.app.jobApp.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserResponseDto createUser(UserRequestDto request) {
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setRole(request.getRole());
        User savedUser = userRepository.save(user);
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setEmail(savedUser.getEmail());
        userResponseDto.setRole(savedUser.getRole());
        System.out.println(userResponseDto);
        return userResponseDto;
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        return List.of();
    }
}
