package com.urbanik.aiservice.service;

import com.urbanik.aiservice.models.Recommendation;

import java.util.List;
import java.util.Optional;

public interface RecommendationService {
    List<Recommendation> getUserRecommendation(String userId);

    Optional<Recommendation> getActivityRecommendation(String activityId);
}
