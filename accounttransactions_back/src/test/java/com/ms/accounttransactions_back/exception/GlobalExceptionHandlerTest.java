package com.ms.accounttransactions_back.exception;

import com.ms.accounttransactions_back.dto.response.ErrorResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.bind.MethodArgumentNotValidException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Collections;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * @author : Freddy Torres
 * file :  GlobalExceptionHandlerTest
 * @since : 3/4/2025, jue
 **/

class GlobalExceptionHandlerTest {
    @InjectMocks
    private GlobalExceptionHandler globalExceptionHandler;

    @Mock
    private AutoCloseable closeable;

    @Mock
    private WebRequest webRequest;

    @BeforeEach
    public void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }
    @AfterEach
    void closeService() throws Exception {
        closeable.close();
    }


    @Test
    public void methodArgumentNotValidException() {
        // Given
        var exception = mock(MethodArgumentNotValidException.class);
        when(exception.getAllErrors()).thenReturn(Collections.singletonList(new org.springframework.validation.ObjectError("field", "Invalid field")));
        // When
        ResponseEntity<ErrorsMessage> response = globalExceptionHandler.notValid(exception, webRequest);
        // Then
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(Objects.requireNonNull(response.getBody()).getMessage().containsKey("errors"));
        assertTrue(response.getBody().getMessage().get("errors").contains("Invalid field"));
    }

    @Test
    public void testHandleClientNotFoundException() {
        // Given
        var message = "Client not found";
        var exception = new NotFoundException(message);
        // When
        ResponseEntity<ErrorResponse> response = globalExceptionHandler.handleClientNotFoundException(exception);
        // Then
        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(message, Objects.requireNonNull(response.getBody()).getMessage());
        assertNotNull(response.getBody().getTimestamp());
    }

    @Test
    public void testHandleSQLIntegrityConstraintViolationException() {
        // Given
        var message = "Duplicate entry for key 'email'";
        var exception = new SQLIntegrityConstraintViolationException(message);
        // When
        ResponseEntity<ErrorResponse> response = globalExceptionHandler.notValid(exception);
        // Then
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(message, Objects.requireNonNull(response.getBody()).getMessage());
        assertNotNull(response.getBody().getTimestamp());
    }
}
