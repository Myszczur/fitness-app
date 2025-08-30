package com.urbanik.activityservice.models.dto;

import com.urbanik.activityservice.models.ActivityType;

import java.time.LocalDateTime;
import java.util.Map;

public record ActivityResponse(
        String id,
        Long userId,
        ActivityType type,
        Integer duration,
        Integer caloriesBurned,
        LocalDateTime startTime,
        Map<String, Object> additionalMetrics,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {
}
