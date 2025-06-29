package com.urbanik.userservice.service;

import com.urbanik.userservice.exceptions.EmailAlreadyExistsException;
import com.urbanik.userservice.models.dto.RegisterRequest;
import com.urbanik.userservice.models.dto.UserResponse;

public interface UserService {
    UserResponse getUserProfile(Long userId);

    UserResponse register(RegisterRequest registerRequest);

    Boolean validateUser(Long userId);
}
