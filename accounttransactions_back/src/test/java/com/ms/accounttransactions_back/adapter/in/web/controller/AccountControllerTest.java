package com.ms.accounttransactions_back.adapter.in.web.controller;

import com.ms.accounttransactions_back.adapter.in.web.dto.ApiResponse;
import com.ms.accounttransactions_back.application.port.in.AccountPort;
import com.ms.accounttransactions_back.domain.Account;
import com.ms.accounttransactions_back.domain.enums.AccountType;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
@SpringBootTest
@ContextConfiguration(classes = EntityManager.class)
class AccountControllerTest {
    private static final Long ACCOUNT_NUMBER = 123L;

    @InjectMocks
    private AccountController accountController;

    @Mock
    private AccountPort accountPort;

    private Account account;

    @BeforeEach
    void init() {
        account = new Account(1L,ACCOUNT_NUMBER, AccountType.AHORRO,200.00,null,true);
    }

    @Test
    void postAccount() {
        // Given
        when(accountPort.saveAccount(account)).thenReturn(account);
        // When
        ResponseEntity<ApiResponse<Account>> response = accountController.postAccount(account);
        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(AccountController.SUCCESS_MESSAGE, response.getBody().getMessage());
        assertEquals(account.getAccountNumber(), response.getBody().getData().getAccountNumber());
    }

    @Test
    void findAllByIdentificationAndStatusTrue() {
        // Given
        Account accountList = account;
        when(accountPort.findByClientIdentification(ACCOUNT_NUMBER)).thenReturn(accountList);
        // When
        ResponseEntity<ApiResponse<Account>> response = accountController.findAllByIdentificationAndStatusTrue(ACCOUNT_NUMBER);
        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(AccountController.SUCCESS_MESSAGE, response.getBody().getMessage());
    }

    @Test
    void updateAccount() {
        // Given
        when(accountPort.updateAccount(ACCOUNT_NUMBER)).thenReturn(account);
        // When
        ResponseEntity<ApiResponse<Account>> response = accountController.updateAccount(ACCOUNT_NUMBER);
        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(AccountController.SUCCESS_MESSAGE, response.getBody().getMessage());
        assertEquals(ACCOUNT_NUMBER, response.getBody().getData().getAccountNumber());
    }
}
