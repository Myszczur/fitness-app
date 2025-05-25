package com.urbanik.userservice.service.impl.mappers;

import com.urbanik.userservice.models.User;
import com.urbanik.userservice.models.UserRole;
import com.urbanik.userservice.models.dto.RegisterRequest;
import com.urbanik.userservice.models.dto.UserDTO;
import com.urbanik.userservice.models.dto.UserResponse;

public class UserMapper {

    public static UserResponse userDtoToUserResponse(UserDTO user) {
        return new UserResponse(user.id(),
                user.email(),
                user.password(),
                user.firstName(),
                user.lastName(),
                user.role(),
                user.createdAt(),
                user.updatedAt());
    }

    public static User registerRequestToUser(RegisterRequest registerRequest) {
        return User.builder()
                .email(registerRequest.email())
                .password(registerRequest.password())
                .firstName(registerRequest.firstName())
                .lastName(registerRequest.lastName())
                .role(UserRole.USER)
                .build();
    }

    public static UserResponse userToUserResponse(User user) {
        return new UserResponse(user.getId(),
                user.getEmail(),
                user.getPassword(),
                user.getFirstName(),
                user.getLastName(),
                user.getRole(),
                user.getCreatedAt(),
                user.getUpdatedAt());
    }
}
