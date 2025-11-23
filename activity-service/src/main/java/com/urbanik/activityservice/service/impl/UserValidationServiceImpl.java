package com.urbanik.activityservice.service.impl;

import com.urbanik.activityservice.exceptions.UserNotFoundException;
import com.urbanik.activityservice.service.UserValidationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserValidationServiceImpl implements UserValidationService {

    private final WebClient userServiceWebClient;

    @Override
    public boolean validateUser(Long userId) {
        try {
            return Boolean.TRUE.equals(userServiceWebClient.get()
                    .uri("api/users/{userId}/validate", userId)
                    .retrieve()
                    .bodyToMono(Boolean.class)
                    .block());
        } catch (WebClientResponseException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND || e.getStatusCode() == HttpStatus.BAD_REQUEST) {
                log.error("User Not Found: {}", e.getLocalizedMessage(), e);
                throw new UserNotFoundException("User Not Fond! -> %s".formatted(e.getLocalizedMessage()));
            }
            return false;
        }
    }
}
