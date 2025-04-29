package com.ms.accounttransactions_back.adapter.out.persistence.mapper;

import com.ms.accounttransactions_back.adapter.out.persistence.entity.AccountEntity;
import com.ms.accounttransactions_back.adapter.out.persistence.entity.TransactionEntity;
import com.ms.accounttransactions_back.domain.Report;
import com.ms.accounttransactions_back.domain.Transaction;
import com.ms.accounttransactions_back.domain.enums.TransactionType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class TransactionMapperTest {
    private Transaction transaction;
    private TransactionEntity transactionEntity;

    private final LocalDateTime now = LocalDateTime.now();

    @BeforeEach
    void setUp() {
        // When
        transaction = new Transaction();
        transaction.setAmount(100.0);
        transaction.setBalance(500.0);
        transaction.setDate(now);
        transaction.setDescription("DEPÓSITO INICIAL");
        transaction.setTransactionType(TransactionType.DEPOSITO);
        transactionEntity = new TransactionEntity();
        transactionEntity.setAmount(100.0);
        transactionEntity.setBalance(500.0);
        transactionEntity.setDate(now);
        transactionEntity.setDescription("DEPÓSITO INICIAL");
        transactionEntity.setTransactionType(TransactionType.DEPOSITO);
        transactionEntity.setAccount(new AccountEntity());
    }

    @Test
    void domainToEntity_shouldMapCorrectly() {
        // When
        TransactionEntity entity = TransactionMapper.domainToEntity(transaction);
        // Then
        assertNotNull(entity);
        assertEquals(transaction.getAmount(), entity.getAmount());
        assertEquals(transaction.getBalance(), entity.getBalance());
        assertEquals(transaction.getDate(), entity.getDate());
        assertEquals(transaction.getDescription(), entity.getDescription());
        assertEquals(transaction.getTransactionType(), entity.getTransactionType());
        assertEquals(transaction.getAccount(), entity.getAccount());
    }

    @Test
    void entityToDomain_shouldMapCorrectly() {
        // When
        Transaction result = TransactionMapper.entityToDomain(transactionEntity);
        // Then
        assertNotNull(result);
        assertEquals(transactionEntity.getAmount(), result.getAmount());
        assertEquals(transactionEntity.getBalance(), result.getBalance());
        assertEquals(transactionEntity.getDate(), result.getDate());
        assertEquals(transactionEntity.getDescription(), result.getDescription());
        assertEquals(transactionEntity.getTransactionType(), result.getTransactionType());
        assertEquals(transactionEntity.getAccount(), result.getAccount());
    }
    @Test
    void entityToReport_shouldMapCorrectly() {
        // When
        Report report = TransactionMapper.entityToReport(transactionEntity);
        // Then
        assertNotNull(report);
        assertEquals(transactionEntity.getAmount(), report.getAmount());
        assertEquals(transactionEntity.getBalance(), report.getBalance());
        assertEquals(transactionEntity.getDate(), report.getDate());
        assertEquals(transactionEntity.getDescription(), report.getDescription());
        assertEquals(transactionEntity.getTransactionType(), report.getTransactionType());
    }
    @Test
    void entityToReport_shouldReturnNull_whenEntityIsNull() {
        // When
        TransactionMapper.entityToReport(null);
        // Then
        assertNull(null);
    }
}
