package com.urbanik.activityservice.repository;


import com.urbanik.activityservice.models.Activity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityRepository extends MongoRepository<Activity, String> {

    List<Activity> findAllByUserId(Long userId);
}
