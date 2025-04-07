package com.ms.accounttransactions_back.controller;

import com.ms.accounttransactions_back.constants.ProcessConstants;
import com.ms.accounttransactions_back.dto.TransactionByAccountNumberDto;
import com.ms.accounttransactions_back.dto.TransactionDto;
import com.ms.accounttransactions_back.dto.response.ApiResponse;
import com.ms.accounttransactions_back.dto.response.ResponseUtil;
import com.ms.accounttransactions_back.service.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : Freddy Torres
 * file :  TransactionController
 * @since : 4/4/2025, vie
 **/

@PropertySource("classpath:messages.properties")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(ProcessConstants.API_PATH_TRANSACTION)
public class TransactionController {
    @Value("${controller.ok}")
    public String CONTROLLER_OK;

    private final TransactionService transactionService;

    @PostMapping
    public ResponseEntity<ApiResponse<TransactionByAccountNumberDto>> createAccount(@Valid @RequestBody TransactionDto transaction) throws Exception {
        var transactionDto = transactionService.saveTransaction(transaction);
        return new ResponseEntity<>(ResponseUtil.createSuccessResponse(CONTROLLER_OK, transactionDto), HttpStatus.CREATED);
    }
}
