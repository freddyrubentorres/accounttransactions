package com.ms.accounttransactions_back.controller;

import com.ms.accounttransactions_back.constants.ProcessConstants;
import com.ms.accounttransactions_back.dto.AccountByAccountNumberDto;
import com.ms.accounttransactions_back.dto.AccountDto;
import com.ms.accounttransactions_back.dto.response.ApiResponse;
import com.ms.accounttransactions_back.dto.response.ResponseUtil;
import com.ms.accounttransactions_back.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;

/**
 * @author : Freddy Torres
 * file :  AccountController
 * @since : 4/4/2025, vie
 **/

@PropertySource("classpath:messages.properties")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(ProcessConstants.API_PATH_ACCOUNTS)
public class AccountController {

    @Value("${controller.ok}")
    public String CONTROLLER_OK;

    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<ApiResponse<AccountDto>> postAccount(@Valid @RequestBody AccountDto account) throws Exception {
        var accountDto = accountService.saveAccount(account);
        return new ResponseEntity<>(ResponseUtil.createSuccessResponse(CONTROLLER_OK, accountDto), HttpStatus.CREATED);
    }

    @GetMapping(ProcessConstants.API_PATH_ACCOUNTS_BY_ACCOUNTNUMBER)
    public ResponseEntity<ApiResponse<AccountByAccountNumberDto>> getAccountByAccountNumber(@PathVariable Long accountNumber) {
        var accountDto = accountService.getAccountByAccountNumber(accountNumber);
        return ResponseEntity.ok(ResponseUtil.createSuccessResponse(CONTROLLER_OK, accountDto));
    }

    @PutMapping(ProcessConstants.API_PATH_ACCOUNTS_PUT_ACCOUNTNUMBER)
    public ResponseEntity<ApiResponse<AccountByAccountNumberDto>> putAccountByAccountNumber(@PathVariable Long accountNumber) throws Exception {
        var accountDto = accountService.putAccountByAccountNumber(accountNumber);
        return ResponseEntity.ok(ResponseUtil.createSuccessResponse(CONTROLLER_OK, accountDto));
    }

}
