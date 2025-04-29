package com.ms.accounttransactions_back.adapter.out.persistence;

import com.ms.accounttransactions_back.adapter.out.persistence.repository.TransactionRepository;
import com.ms.accounttransactions_back.application.exception.NotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.*;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TransactionPersistenceAdapterTest {
    @InjectMocks
    private TransactionPersistenceAdapter transactionPersistenceAdapter;

    @Mock
    private TransactionRepository transactionRepository;

    @Test
    void findTransactionsByIdentificationAndDateRange_shouldThrowException_whenNoTransactionsFound() {
        // Given
        String identification = "12345";
        String startDate = "2025-01-01";
        String endDate = "2025-12-31";
        LocalDateTime start = LocalDateTime.parse("2025-01-01T00:00:00");
        LocalDateTime end = LocalDateTime.parse("2025-12-31T23:00:00");
        // When
        when(transactionRepository.findTransactionsByIdentificationAndDateRange(identification, start, end))
                .thenReturn(Collections.emptyList());
        // Then
        assertThrows(NotFoundException.class, () -> {
            transactionPersistenceAdapter.findTransactionsByIdentificationAndDateRange(identification, startDate, endDate);
        });
    }
}
