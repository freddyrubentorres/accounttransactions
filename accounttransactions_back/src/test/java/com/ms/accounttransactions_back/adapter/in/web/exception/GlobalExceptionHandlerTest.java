package com.ms.accounttransactions_back.adapter.in.web.exception;

import com.ms.accounttransactions_back.adapter.in.web.util.ErrorsMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.WebRequest;

import java.lang.reflect.Method;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.ms.accounttransactions_back.adapter.in.web.dto.ErrorResponse;
import com.ms.accounttransactions_back.application.exception.NotFoundException;


class GlobalExceptionHandlerTest {
    private GlobalExceptionHandler globalExceptionHandler;
    private ErrorResponseFactory errorResponseFactory;

    @BeforeEach
    void setUp() {
        errorResponseFactory = mock(ErrorResponseFactory.class);
        globalExceptionHandler = new GlobalExceptionHandler(errorResponseFactory);
    }

    @Test
    void testHandleUserNotFound_shouldReturnNotFoundResponse() {
        // Given
        String exceptionMessage = "Usuario no encontrado";
        NotFoundException notFoundException = new NotFoundException(exceptionMessage);
        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(false)
                .message(exceptionMessage)
                .timestamp(null)
                .build();
        when(errorResponseFactory.build(HttpStatus.NOT_FOUND, exceptionMessage)).thenReturn(new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND));
        // When
        ResponseEntity<ErrorResponse> responseEntity = globalExceptionHandler.handleUserNotFound(notFoundException);
        // Then
        assertNotNull(responseEntity, "La respuesta no debe ser nula");
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode(), "El código de estado HTTP no es el esperado");
        verify(errorResponseFactory).build(HttpStatus.NOT_FOUND, exceptionMessage);
    }

    @Test
    void testNotValid_shouldReturnBadRequestResponseWithErrorMessages() throws Exception {
        // Given
        ObjectError error1 = new ObjectError("email", "Email es inválido");
        ObjectError error2 = new ObjectError("password", "Password es requerido");
        List<ObjectError> errors = List.of(error1, error2);
        var bindingResult = mock(org.springframework.validation.BindingResult.class);
        when(bindingResult.getAllErrors()).thenReturn(errors);
        // Crear MethodArgumentNotValidException falso
        Method dummyMethod = this.getClass().getDeclaredMethod("testNotValid_shouldReturnBadRequestResponseWithErrorMessages");
        var methodParameter = new org.springframework.core.MethodParameter(dummyMethod, -1);
        MethodArgumentNotValidException ex = new MethodArgumentNotValidException(methodParameter, bindingResult);
        // WebRequest simulado
        WebRequest webRequest = mock(WebRequest.class);
        when(webRequest.getDescription(false)).thenReturn("uri=/api/test");
        // When
        ResponseEntity<ErrorsMessage> response = globalExceptionHandler.notValid(ex, webRequest);
        // Then
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        ErrorsMessage body = response.getBody();
        assertNotNull(body);
        assertEquals(HttpStatus.BAD_REQUEST.value(), body.getStatusCode());
        assertEquals("uri=/api/test", body.getDescription());
        Map<String, List<String>> messages = body.getMessage();
        assertNotNull(messages);
        assertTrue(messages.containsKey("errors"));
        List<String> errorMessages = messages.get("errors");
        assertEquals(2, errorMessages.size());
        assertTrue(errorMessages.contains("Email es inválido"));
        assertTrue(errorMessages.contains("Password es requerido"));
        assertNotNull(body.getTimestamp());
    }

    @Test
    void testNotValid_EmailConstraintViolation_ShouldReturnCustomMessage() {
        SQLIntegrityConstraintViolationException ex = new SQLIntegrityConstraintViolationException("duplicate key uk_client_email");
        ResponseEntity<ErrorResponse> response = globalExceptionHandler.notValid(ex);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Ya existe correo electrónico ", response.getBody().getMessage());
    }

    @Test
    void testNotValid_IdentificationConstraintViolation_ShouldReturnCustomMessage() {
        SQLIntegrityConstraintViolationException ex = new SQLIntegrityConstraintViolationException("duplicate key uk_person_identification");
        ResponseEntity<ErrorResponse> response = globalExceptionHandler.notValid(ex);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Ya existe identificacion ", response.getBody().getMessage());
    }

    @Test
    void testNotValid_OtherConstraintViolation_ShouldIncludeOriginalMessage() {
        String originalMessage = "some other SQL constraint violation";
        SQLIntegrityConstraintViolationException ex = new SQLIntegrityConstraintViolationException(originalMessage);
        ResponseEntity<ErrorResponse> response = globalExceptionHandler.notValid(ex);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().getMessage().contains(originalMessage));
    }

    @Test
    void testHandleIllegalArgumentException_ShouldReturnBadRequestWithMessage() {
        String errorMessage = "El argumento no es válido";
        IllegalArgumentException ex = new IllegalArgumentException(errorMessage);
        ResponseEntity<ErrorResponse> response = globalExceptionHandler.handleIllegalArgumentException(ex);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(errorMessage, response.getBody().getMessage());
    }
}