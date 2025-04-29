package com.ms.accounttransactions_back.adapter.in.web.exception;

import com.ms.accounttransactions_back.adapter.in.web.dto.ErrorResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

class ErrorResponseFactoryTest {
    private ErrorResponseFactory errorResponseFactory;

    @BeforeEach
    void setUp() {
        errorResponseFactory = new ErrorResponseFactory();
    }

    @Test
    void testBuild_withValidMessage_shouldReturnCorrectErrorResponse() {
        // Given
        String message = "Something went wrong";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        // When
        ResponseEntity<ErrorResponse> responseEntity = errorResponseFactory.build(status, message);
        ErrorResponse errorResponse = responseEntity.getBody();
        // Then
        assertNotNull(errorResponse, "ErrorResponse should not be null");
        assertEquals(message, errorResponse.getMessage(), "Message should match");
        assertNotNull(errorResponse.getTimestamp(), "Timestamp should not be null");
        assertEquals(status, responseEntity.getStatusCode(), "HTTP status should match");
    }
}
