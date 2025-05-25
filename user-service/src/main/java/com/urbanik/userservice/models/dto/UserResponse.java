package com.urbanik.userservice.models.dto;

import com.urbanik.userservice.models.UserRole;

import java.time.LocalDateTime;

public record UserResponse(
        Long id,
        String email,
        String password,
        String firstName,
        String lastName,
        UserRole role,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}