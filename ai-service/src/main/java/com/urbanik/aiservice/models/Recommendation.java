package com.urbanik.aiservice.models;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "recommendations")
public class Recommendation {

    @Id
    private String id;
    private String activityId;
    private String userId;
    private String activityType;
    private String recommendation;

    @Builder.Default
    private List<String> improvements = new ArrayList<>();
    @Builder.Default
    private List<String> suggestion = new ArrayList<>();
    @Builder.Default
    private List<String> safety = new ArrayList<>();

    @CreatedDate
    private LocalDateTime createdAt;
}

