package com.ecommerce.commons.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ecommerce.commons.dto.ErrorResponse;

import feign.FeignException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Handles Feign errors across microservices
    @ExceptionHandler(FeignException.class)
    public ResponseEntity<ErrorResponse> handleFeignException(FeignException ex) {
        ErrorResponse response = new ErrorResponse(
                LocalDateTime.now(),
                "Error communicating with another service",
                ex.getMessage()
        );
        HttpStatus status = HttpStatus.resolve(ex.status());
        return new ResponseEntity<>(response, status != null ? status : HttpStatus.BAD_GATEWAY);
    }

    // Handles any other unhandled exception
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneralException(Exception ex) {
        ErrorResponse response = new ErrorResponse(
                LocalDateTime.now(),
                "Unexpected error occurred",
                ex.getMessage()
        );
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
