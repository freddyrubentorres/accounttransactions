package com.ms.accounttransactions_back.application.service;

import com.ms.accounttransactions_back.application.port.out.LoadTransactionPort;
import com.ms.accounttransactions_back.domain.Report;
import com.ms.accounttransactions_back.domain.Transaction;
import com.ms.accounttransactions_back.domain.enums.TransactionType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TransactionServiceTest {
    @Mock
    private LoadTransactionPort loadTransactionPort;

    @InjectMocks
    private TransactionService transactionService;

    private Transaction transaction;
    private Map<Long, List<Report>> mockReport;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        transaction = new Transaction();
        transaction.setAmount(150.0);
        transaction.setBalance(500.0);
        transaction.setDescription("DEPOSITO");
        transaction.setTransactionType(TransactionType.DEPOSITO);
        transaction.setDate(LocalDateTime.now());
        mockReport = Map.of(
                1L, List.of(new Report()),
                2L, List.of(new Report())
        );
    }

    @Test
    void findTransactionsByIdentificationAndDateRange_shouldReturnReports() {
        // Given
        String identification = "17154871548";
        String startDate = "2023-01-01";
        String endDate = "2023-12-31";
        when(loadTransactionPort.findTransactionsByIdentificationAndDateRange(identification, startDate, endDate)).thenReturn(mockReport);
        // When
        Map<Long, List<Report>> result = transactionService.findTransactionsByIdentificationAndDateRange(identification, startDate, endDate);
        // Then
        assertNotNull(result);
        assertEquals(mockReport, result);
        assertFalse(result.isEmpty());
        verify(loadTransactionPort, times(1)).findTransactionsByIdentificationAndDateRange(identification, startDate, endDate);
    }

    @Test
    void saveTransaction_shouldReturnSavedTransaction() {
        // When
        when(loadTransactionPort.saveTransaction(transaction)).thenReturn(transaction);
        Transaction result = transactionService.saveTransaction(transaction);
        // Then
        assertNotNull(result);
        assertEquals(transaction.getAmount(), result.getAmount());
        verify(loadTransactionPort).saveTransaction(transaction);
    }

}
