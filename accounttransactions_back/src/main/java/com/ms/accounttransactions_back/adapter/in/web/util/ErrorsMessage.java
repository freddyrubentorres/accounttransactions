package com.ms.accounttransactions_back.adapter.in.web.util;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;


/**
 * @author : Freddy Torres
 * file : ErrorsMessage
 * @since : 25/4/2025, vie
 **/

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorsMessage {
    private int statusCode;
    private LocalDateTime timestamp;
    private Map<String, List<String>> message;
    private String description;
}
