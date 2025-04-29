package com.ms.accounttransactions_back.adapter.in.web.controller;

import com.ms.accounttransactions_back.adapter.in.web.dto.ApiResponse;
import com.ms.accounttransactions_back.application.port.in.TransactionPort;
import com.ms.accounttransactions_back.domain.Transaction;
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
class TransactionControllerTest {
    @InjectMocks
    private TransactionController transactionController;

    @Mock
    private TransactionPort transactionPort;

    private Transaction transaction;

    @BeforeEach
    void init() {
        transaction = new Transaction();
    }

    @Test
    void postAccount() {
        // Given
        when(transactionPort.saveTransaction(transaction)).thenReturn(transaction);
        // When
        ResponseEntity<ApiResponse<Transaction>> response = transactionController.postAccount(transaction);
        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(AccountController.SUCCESS_MESSAGE, response.getBody().getMessage());
    }
}
