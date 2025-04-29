package com.ms.accounttransactions_back.adapter.in.web.exception;

import com.ms.accounttransactions_back.adapter.in.web.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author : Freddy Torres
 * file : ErrorResponseFactory
 * @since : 24/4/2025, jue
 **/

@Component
public class ErrorResponseFactory {
    public ResponseEntity<ErrorResponse> build(HttpStatus status, String message) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(false)
                .message(message)
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(errorResponse, status);
    }
}
