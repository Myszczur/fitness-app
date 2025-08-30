package com.urbanik.activityservice.service.impl;

import com.urbanik.activityservice.service.UserValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

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
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                System.err.println("Errpr " + e.getLocalizedMessage());
                throw new RuntimeException("User Not Fond! ", e);
            } else if (e.getStatusCode() == HttpStatus.BAD_REQUEST) {
                System.err.println("Errpr " + e.getLocalizedMessage());
                throw new RuntimeException("User Not Fond! ", e);
            }
            return false;
        }
    }
}
