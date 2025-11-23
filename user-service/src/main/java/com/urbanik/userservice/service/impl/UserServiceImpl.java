package com.urbanik.userservice.service.impl;

import com.urbanik.userservice.exceptions.EmailAlreadyExistsException;
import com.urbanik.userservice.exceptions.UserNotFoundException;
import com.urbanik.userservice.models.User;
import com.urbanik.userservice.models.dto.RegisterRequest;
import com.urbanik.userservice.models.dto.UserResponse;
import com.urbanik.userservice.repository.UserRepository;
import com.urbanik.userservice.service.UserService;
import com.urbanik.userservice.service.impl.mappers.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserResponse getUserProfile(Long userId) {
        var user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException("Provided User not found!")
        );
        return UserMapper.userToUserResponse(user);
    }

    @Override
    public UserResponse register(RegisterRequest registerRequest) {
        checkUserEmail(registerRequest);
        var newUser = saveUserInDb(registerRequest);
        log.info("New User Created: {}", newUser);
        return UserMapper.userToUserResponse(newUser);
    }

    @Override
    public Boolean validateUser(Long userId) {
        log.info("Calling User Validation API for id: {}", userId);
        return userRepository.existsById(userId);
    }

    private void checkUserEmail(RegisterRequest registerRequest) {
        if (userRepository.existsByEmail(registerRequest.email())) {
            log.error("Email already exists! {}", registerRequest.email()); 
            throw new EmailAlreadyExistsException("Provided email already exists!");
        }
    }

    private User saveUserInDb(RegisterRequest registerRequest) {
        var newUser = UserMapper.registerRequestToUser(registerRequest);
        return userRepository.save(newUser);
    }
}
