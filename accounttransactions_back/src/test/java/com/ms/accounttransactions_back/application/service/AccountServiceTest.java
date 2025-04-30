package com.ms.accounttransactions_back.application.service;

import com.ms.accounttransactions_back.adapter.out.persistence.entity.ClientEntity;
import com.ms.accounttransactions_back.application.port.out.LoadAccountPort;
import com.ms.accounttransactions_back.domain.Account;
import com.ms.accounttransactions_back.domain.enums.AccountType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class AccountServiceTest {
    private LoadAccountPort loadAccountPort;
    private AccountService accountService;
    private Account account;
    Account mockAccount;

    @BeforeEach
    void setUp() {
        loadAccountPort = mock(LoadAccountPort.class);
        accountService = new AccountService(loadAccountPort);
        account = new Account(1L, 123456L, AccountType.AHORRO, 100.00,
                new ClientEntity(),
                true);
        mockAccount =
                new Account(
                        1L, 123456L, AccountType.AHORRO, 100.00,
                        new ClientEntity(),
                        true);
    }

    @Test
    void shouldReturnFindByClientIdentification() {
        // When
        when(loadAccountPort.findByClientIdentification(123456L)).thenReturn(mockAccount);
        Account result = accountService.findByClientIdentification(123456L);
        // Then
        assertNotNull(result);
        verify(loadAccountPort, times(1)).findByClientIdentification(123456L);
    }

    @Test
    void shouldReturnSaveAccount() {
        // When
        when(loadAccountPort.saveAccount(account)).thenReturn(account);
        Account result = accountService.saveAccount(account);
        // Then
        assertNotNull(result);
        verify(loadAccountPort, times(1)).saveAccount(account);
    }

    @Test
    void shouldReturnUpdateAccount() {
        // When
        when(loadAccountPort.updateAccount(1L)).thenReturn(account);
        Account result = accountService.updateAccount(1L);
        // Then
        assertNotNull(result);
        verify(loadAccountPort, times(1)).updateAccount(1L);
    }
}
