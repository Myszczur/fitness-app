package com.urbanik.userservice.exceptions;

import com.urbanik.userservice.models.dto.ErrorResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        var errors = ex.getBindingResult().getFieldErrors()
                .stream()
                .collect(Collectors.toMap(
                        FieldError::getField,
                        FieldError::getDefaultMessage
//                        (msg1, msg2) -> msg1 + "; " + msg2
                ));

        log.error("Errors -> {}", errors.entrySet().stream().toList());
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDTO> handlePatientEmailAlreadyExistsException(EmailAlreadyExistsException ex, HttpServletRequest request) {
        var errorResponse = new ErrorResponseDTO(
                HttpStatus.CONFLICT.value(),
                "Email already in use!",
                ex.getMessage(),
                request.getRequestURI(),
                Instant.now()
        );

        log.warn("Email already exists! {}", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handlePatientNotExistsException(UserNotFoundException ex, HttpServletRequest request) {
        var error = new ErrorResponseDTO(
                HttpStatus.NOT_FOUND.value(),
                "User not Found!",
                ex.getMessage(),
                request.getRequestURI(),
                Instant.now()
        );

        log.error("User not Found! {}", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
