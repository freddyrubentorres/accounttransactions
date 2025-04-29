package com.ms.accounttransactions_back.adapter.in.web.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;

/**
 * @author : Freddy Torres
 * file : ApiResponse
 * @since : 24/4/2025, jue
 **/


@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    private boolean success;
    private String message;
    private T data;
    private LocalDateTime timestamp;
}