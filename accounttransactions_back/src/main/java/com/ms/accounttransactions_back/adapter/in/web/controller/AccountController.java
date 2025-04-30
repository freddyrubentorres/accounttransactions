package com.ms.accounttransactions_back.adapter.in.web.controller;

import com.ms.accounttransactions_back.adapter.in.web.dto.ApiResponse;
import com.ms.accounttransactions_back.adapter.in.web.util.ResponseUtil;
import com.ms.accounttransactions_back.application.port.in.AccountPort;
import com.ms.accounttransactions_back.domain.Account;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author : Freddy Torres
 * file : AccountController
 * @since : 25/4/2025, vie
 **/

@RestController
@RequiredArgsConstructor
@RequestMapping("/accounts")
public class AccountController {
    private final AccountPort accountPort;
    public static final String SUCCESS_MESSAGE = "Operacion ejecutada correctamente";

    @PostMapping
    public ResponseEntity<ApiResponse<Account>> postAccount(@Valid @RequestBody Account accountRequest) {
        var account = accountPort.saveAccount(accountRequest);
        return ResponseEntity.ok(ResponseUtil.createSuccessResponse(SUCCESS_MESSAGE, account));
    }
    @GetMapping("/identification/{identification}")
    public ResponseEntity<ApiResponse<Account>> findAllByIdentificationAndStatusTrue(@PathVariable Long identification) {
        var account = accountPort.findByClientIdentification(identification);
        return ResponseEntity.ok(ResponseUtil.createSuccessResponse(SUCCESS_MESSAGE, account));
    }
    @PatchMapping("/accountNumber/{accountNumber}")
    public ResponseEntity<ApiResponse<Account>> updateAccount(@PathVariable Long accountNumber) {
        var account = accountPort.updateAccount(accountNumber);
        return ResponseEntity.ok(ResponseUtil.createSuccessResponse(SUCCESS_MESSAGE, account));
    }
}
