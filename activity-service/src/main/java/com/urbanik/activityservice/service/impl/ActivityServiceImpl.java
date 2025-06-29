package com.urbanik.activityservice.service.impl;


import com.urbanik.activityservice.exceptions.InvalidUserInputException;
import com.urbanik.activityservice.exceptions.ResourceNotFoundException;
import com.urbanik.activityservice.models.Activity;
import com.urbanik.activityservice.models.dto.ActivityRequest;
import com.urbanik.activityservice.models.dto.ActivityResponse;
import com.urbanik.activityservice.repository.ActivityRepository;
import com.urbanik.activityservice.service.ActivityService;
import com.urbanik.activityservice.service.UserValidationService;
import com.urbanik.activityservice.utils.ActivityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ActivityServiceImpl implements ActivityService {

    private final ActivityRepository activityRepository;
    private final UserValidationService userValidationService;


    @Override
    public ActivityResponse trackActivity(ActivityRequest activityRequest) {
        var userIsValid = userValidationService.validateUser(activityRequest.userId());
        if (!userIsValid) {
            throw new InvalidUserInputException("Invalid User!: " + activityRequest.userId());
        }

        var activity = getActivity(activityRequest);
        var savedActivity = activityRepository.save(activity);

        return ActivityMapper.activityToActivityResponse(savedActivity);
    }

    @Override
    public List<ActivityResponse> getUserActivities(String userId) {
        long numericUserId;
        try {
            numericUserId = Long.parseLong(userId);
        } catch (NumberFormatException e) {
            throw new InvalidUserInputException("User ID must be a numeric value: " + userId);
        }
        return activityRepository.findAllByUserId(numericUserId)
                .stream()
                .map(ActivityMapper::activityToActivityResponse)
                .toList();
    }

    @Override
    public ActivityResponse getActivityById(String activityId) {
        return activityRepository.findById(activityId)
                .map(ActivityMapper::activityToActivityResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Activity not found! Id:" + activityId));
    }

    private Activity getActivity(ActivityRequest activityRequest) {
        return Activity.builder()
                .userId(activityRequest.userId())
                .additionalMetrics(activityRequest.additionalMetrics())
                .type(activityRequest.activityType())
                .caloriesBurned(activityRequest.caloriesBurned())
                .startTime(activityRequest.startTime())
                .duration(activityRequest.duration())
                .build();
    }
}
