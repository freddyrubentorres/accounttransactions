package com.ms.accounttransactions_back.adapter.in.web.controller;

import com.ms.accounttransactions_back.domain.TransactionByAccountNumber;
import com.ms.accounttransactions_back.domain.Transaction;
import com.ms.accounttransactions_back.adapter.in.web.dto.ApiResponse;
import com.ms.accounttransactions_back.adapter.in.web.dto.ResponseUtil;
import com.ms.accounttransactions_back.application.service.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
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

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("${endpoint.transactions.base}")
public class TransactionController {
    @Value("${controller.ok}")
    public String mensaje;

    private final TransactionService transactionService;

    @PostMapping
    public ResponseEntity<ApiResponse<TransactionByAccountNumber>> createAccount(@Valid @RequestBody Transaction transaction){
        var transactionDto = transactionService.saveTransaction(transaction);
        return new ResponseEntity<>(ResponseUtil.createSuccessResponse(mensaje, transactionDto), HttpStatus.CREATED);
    }
}
