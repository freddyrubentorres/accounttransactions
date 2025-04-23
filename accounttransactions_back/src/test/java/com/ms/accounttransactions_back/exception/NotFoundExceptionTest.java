package com.ms.accounttransactions_back.exception;

import com.ms.accounttransactions_back.adapter.in.web.exception.NotFoundException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author : Freddy Torres
 * file :  NotFoundExceptionTest
 * @since : 3/4/2025, jue
 **/

class NotFoundExceptionTest {
    @Test
    void testNotFoundExceptionConstructor() {
        // Given
        var expectedMessage = "Client not found";
        // When
        var exception = new NotFoundException(expectedMessage);
        // Then
        assertNotNull(exception);
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void testNotFoundExceptionMessage() {
        // Given
        var message = "Entity not found";
        // When
        var exception = new NotFoundException(message);
        // Then
        assertEquals("Entity not found", exception.getMessage(), "The exception message should match the expected one");
    }
}

