package com.urbanik.userservice.controllers;


import com.urbanik.userservice.models.dto.RegisterRequest;
import com.urbanik.userservice.models.dto.UserResponse;
import com.urbanik.userservice.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
@Tag(name = "User", description = "API for managing users")
public class UserController {

    private final UserService userService;


    @GetMapping("/{userId}")
    @Operation(summary = "Get User Profile")
    public ResponseEntity<UserResponse> getUserProfile(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getUserProfile(userId));
    }

    @PostMapping("/register")
    @Operation(summary = "Register new User")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody RegisterRequest registerRequest) {
        return ResponseEntity.ok(userService.register(registerRequest));
    }

    @GetMapping("/{userId}/validate")
    @Operation(summary = "Get User Profile")
    public ResponseEntity<Boolean> validateUser(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.validateUser(userId));
    }
}
