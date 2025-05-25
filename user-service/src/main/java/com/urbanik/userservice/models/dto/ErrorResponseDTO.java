package com.urbanik.userservice.models.dto;

import java.time.Instant;

public record ErrorResponseDTO(
        int status,
        String error,
        String message,
        String path,
        Instant timestamp
) {
}
