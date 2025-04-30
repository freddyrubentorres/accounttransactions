package com.ms.accounttransactions_back.adapter.in.web.controller;

import com.ms.accounttransactions_back.adapter.in.web.dto.ApiResponse;
import com.ms.accounttransactions_back.adapter.in.web.util.ResponseUtil;
import com.ms.accounttransactions_back.application.port.in.TransactionPort;
import com.ms.accounttransactions_back.domain.Transaction;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : Freddy Torres
 * file : TransactionController
 * @since : 25/4/2025, vie
 **/

@RestController
@RequiredArgsConstructor
@RequestMapping("/transactions")
public class TransactionController {
    private final TransactionPort transactionPort;
    public static final String SUCCESS_MESSAGE = "Operacion ejecutada correctamente";

    @PostMapping
    public ResponseEntity<ApiResponse<Transaction>> postAccount(@Valid @RequestBody Transaction transactionRequest) {
        var transaction = transactionPort.saveTransaction(transactionRequest);
        return ResponseEntity.ok(ResponseUtil.createSuccessResponse(SUCCESS_MESSAGE, transaction));
    }
}
