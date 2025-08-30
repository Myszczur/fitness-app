package com.urbanik.activityservice.models.dto;

import com.urbanik.activityservice.models.ActivityType;

import java.time.LocalDateTime;
import java.util.Map;

public record ActivityRequest(
        Long userId,
        ActivityType activityType,
        Integer duration,
        Integer caloriesBurned,
        LocalDateTime startTime,
        Map<String, Object> additionalMetrics
) {
}
