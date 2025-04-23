package com.ms.accounttransactions_back.adapter.in.web.controller;

import com.ms.accounttransactions_back.domain.AccountByAccountNumber;
import com.ms.accounttransactions_back.domain.Account;
import com.ms.accounttransactions_back.adapter.in.web.dto.ApiResponse;
import com.ms.accounttransactions_back.adapter.in.web.dto.ResponseUtil;
import com.ms.accounttransactions_back.application.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;

/**
 * @author : Freddy Torres
 * file :  AccountController
 * @since : 4/4/2025, vie
 **/

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("${endpoint.accounts.base}")
public class AccountController {

    @Value("${controller.ok}")
    public String mensaje;

    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<ApiResponse<Account>> postAccount(@Valid @RequestBody Account account)  {
        var accountDto = accountService.saveAccount(account);
        return new ResponseEntity<>(ResponseUtil.createSuccessResponse(mensaje, accountDto), HttpStatus.CREATED);
    }

    @GetMapping("/accountNumber/{accountNumber}")
    public ResponseEntity<ApiResponse<AccountByAccountNumber>> getAccountByAccountNumber(@PathVariable Long accountNumber) {
        var accountDto = accountService.getAccountByAccountNumber(accountNumber);
        return ResponseEntity.ok(ResponseUtil.createSuccessResponse(mensaje, accountDto));
    }

    @PutMapping("/{accountNumber}")
    public ResponseEntity<ApiResponse<AccountByAccountNumber>> putAccountByAccountNumber(@PathVariable Long accountNumber) {
        var accountDto = accountService.putAccountByAccountNumber(accountNumber);
        return ResponseEntity.ok(ResponseUtil.createSuccessResponse(mensaje, accountDto));
    }

}
