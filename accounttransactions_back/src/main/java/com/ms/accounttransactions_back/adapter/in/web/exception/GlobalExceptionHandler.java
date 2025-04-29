package com.ms.accounttransactions_back.adapter.in.web.exception;

import com.ms.accounttransactions_back.adapter.in.web.dto.ErrorResponse;
import com.ms.accounttransactions_back.adapter.in.web.util.ErrorsMessage;
import lombok.AllArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.ms.accounttransactions_back.application.exception.*;
import org.springframework.web.context.request.WebRequest;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author : Freddy Torres
 * file : GlobalExceptionHandler
 * @since : 24/4/2025, jue
 **/
@ControllerAdvice
@AllArgsConstructor
public class GlobalExceptionHandler {

    private final ErrorResponseFactory errorResponseFactory;

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFound(NotFoundException ex) {
        return errorResponseFactory.build(HttpStatus.NOT_FOUND, ex.getMessage());
    }



    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorsMessage> notValid(MethodArgumentNotValidException ex, WebRequest request) {
        List<String> errors = ex.getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();
        Map<String, List<String>> result = Collections.singletonMap("errors", errors);
        ErrorsMessage message = new ErrorsMessage();
        message.setStatusCode(HttpStatus.BAD_REQUEST.value());
        message.setTimestamp(LocalDateTime.now());
        message.setMessage(result);
        message.setDescription(request.getDescription(false));
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> notValid(SQLIntegrityConstraintViolationException ex) {
        String message = ex.getMessage();
        if (message != null && message.contains("uk_client_email")) {
            return createErrorResponse(HttpStatus.BAD_REQUEST, "Ya existe correo electrónico ");
        }
        if (message != null && message.contains("uk_person_identification")) {
            return createErrorResponse(HttpStatus.BAD_REQUEST, "Ya existe identificacion ");
        }
        return createErrorResponse(HttpStatus.BAD_REQUEST, "Violación de restricción de integridad: " + ex.getMessage());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleInvalidEnum(HttpMessageNotReadableException ex) {
        String message = ex.getMessage();
        if (message != null && message.contains("Gender")) {
            return createErrorResponse(HttpStatus.BAD_REQUEST,
                    "Género inválido. Los valores permitidos son: 'M' para masculino o 'F' para femenino.");
        }
        return createErrorResponse(HttpStatus.BAD_REQUEST,
                "Solicitud malformada. Verifica los datos enviados.");
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
        return createErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }
    private ResponseEntity<ErrorResponse> createErrorResponse(HttpStatus status, String message) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(false)
                .message(message)
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(errorResponse, status);
    }
}
