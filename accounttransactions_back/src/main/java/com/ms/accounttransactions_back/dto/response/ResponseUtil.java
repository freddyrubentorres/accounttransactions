package com.ms.accounttransactions_back.dto.response;

import java.time.LocalDateTime;

/**
 * @author : Freddy Torres
 * file :  ResponseUtil
 * @since : 3/4/2025, jue
 **/

public class ResponseUtil {
    public static <T> ApiResponse<T> createSuccessResponse(String message, T data) {
        return ApiResponse.<T>builder()
                .success(true)
                .message(message)
                .data(data)
                .timestamp(LocalDateTime.now())
                .build();
    }
}
