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
import org.springframework.stereotype.Service;

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
        var userResponse = saveUserInDb(registerRequest);
        return UserMapper.userToUserResponse(userResponse);
    }

    private void checkUserEmail(RegisterRequest registerRequest) {
        if (userRepository.existsByEmail(registerRequest.email())) {
            throw new EmailAlreadyExistsException("Provided email already exists!");
        }
    }

    //TODO: unowoześ nić

    private User saveUserInDb(RegisterRequest registerRequest) {
        var newUser = UserMapper.registerRequestToUser(registerRequest);
        return userRepository.save(newUser);
    }
}
