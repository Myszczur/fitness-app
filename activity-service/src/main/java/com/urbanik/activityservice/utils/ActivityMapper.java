package com.urbanik.activityservice.utils;

import com.urbanik.activityservice.models.Activity;
import com.urbanik.activityservice.models.dto.ActivityResponse;

public class ActivityMapper {


    public static ActivityResponse activityToActivityResponse(Activity savedActivity) {
        return new ActivityResponse(savedActivity.getId(),
                savedActivity.getUserId(),
                savedActivity.getType(),
                savedActivity.getDuration(),
                savedActivity.getCaloriesBurned(),
                savedActivity.getStartTime(),
                savedActivity.getAdditionalMetrics(),
                savedActivity.getCreatedAt(),
                savedActivity.getUpdatedAt());
    }
}
