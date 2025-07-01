package com.urbanik.activityservice.service;

import com.urbanik.activityservice.models.dto.ActivityRequest;
import com.urbanik.activityservice.models.dto.ActivityResponse;

import java.util.List;

public interface ActivityService {
    ActivityResponse trackActivity(ActivityRequest activityRequest);

    List<ActivityResponse> getUserActivities(String userId);

    ActivityResponse getActivityById(String activityId);
}
