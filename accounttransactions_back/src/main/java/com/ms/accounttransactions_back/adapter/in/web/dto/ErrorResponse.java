package com.ms.accounttransactions_back.adapter.in.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;


/**
 * @author : Freddy Torres
 * file : ErrorResponse
 * @since : 24/4/2025, jue
 **/

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ErrorResponse {
    private Boolean status;
    private String message;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime timestamp;
}