package com.ms.accounttransactions_back.application.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


class NotFoundExceptionTest {
    @Test
    void testNotFoundExceptionConstructor() {
        // Given
        var expectedMessage = "not found";
        // When
        var exception = new NotFoundException(expectedMessage);
        // Then
        assertNotNull(exception);
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void testNotFoundExceptionMessage() {
        // Given
        var message = "not found";
        // When
        var exception = new NotFoundException(message);
        // Then
        assertEquals("not found", exception.getMessage(), "The exception message should match the expected one");
    }
}