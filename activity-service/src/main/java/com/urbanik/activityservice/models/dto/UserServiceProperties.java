package com.urbanik.activityservice.models.dto;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;
import jakarta.validation.constraints.NotBlank;

@Validated
@ConfigurationProperties(prefix = "app.services.user")
public record UserServiceProperties(@NotBlank String baseUrl) {
}
