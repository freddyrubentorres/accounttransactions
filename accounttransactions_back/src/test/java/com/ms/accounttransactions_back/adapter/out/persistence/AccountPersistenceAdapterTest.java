package com.ms.accounttransactions_back.adapter.out.persistence;

import static org.junit.jupiter.api.Assertions.*;

import com.ms.accounttransactions_back.adapter.out.persistence.entity.ClientEntity;
import com.ms.accounttransactions_back.adapter.out.persistence.entity.TransactionEntity;
import com.ms.accounttransactions_back.application.exception.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.ms.accounttransactions_back.adapter.out.persistence.entity.AccountEntity;
import com.ms.accounttransactions_back.adapter.out.persistence.mapper.AccountMapper;
import com.ms.accounttransactions_back.adapter.out.persistence.repository.AccountRepository;
import com.ms.accounttransactions_back.domain.Account;
import com.ms.accounttransactions_back.domain.enums.AccountType;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountPersistenceAdapterTest {
    @Mock
    private AccountRepository accountRepository;
    @InjectMocks
    private AccountPersistenceAdapter adapter;
    private AccountEntity accountEntity;

    @BeforeEach
    void setUp() {
        Account account = new Account(null, 123L, AccountType.AHORRO, 500.0, new ClientEntity(), true);
        accountEntity = AccountMapper.domainToEntity(account);
        accountEntity = new AccountEntity();
        accountEntity.setAccountId(1L);
        accountEntity.setAccountNumber(123456L);
        accountEntity.setAccountType(AccountType.AHORRO);
        accountEntity.setInitialBalance(500.0);
        accountEntity.setClient(new ClientEntity());
        accountEntity.setStatus(true);
        AccountEntity savedEntity = new AccountEntity();
        savedEntity.setAccountId(1L);
        savedEntity.setAccountNumber(123456L);
        savedEntity.setInitialBalance(500.0);
        savedEntity.setStatus(true);
        savedEntity.setAccountType(AccountType.AHORRO);
    }

    @Test
    void findByClientIdentification_shouldReturnAccountList() {
        // Given
        String identification = "1234567890";
        when(accountRepository.findByClientIdentification(identification))
                .thenReturn(List.of(accountEntity));
        // When
        List<Account> result = adapter.findByClientIdentification(identification);
        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(accountEntity.getAccountNumber(), result.get(0).getAccountNumber());
    }

    @Test
    void findByClientIdentification_shouldThrowNotFoundException_whenEmpty() {
        // Given
        String identification = "1234567890";
        when(accountRepository.findByClientIdentification(identification))
                .thenReturn(Collections.emptyList());
        // Then
        NotFoundException exception = assertThrows(NotFoundException.class, () ->
                adapter.findByClientIdentification(identification));
        assertEquals("Informacion no encontrada", exception.getMessage());
    }

    @Test
    void findByClientIdentification_ShouldThrow_WhenEmpty() {
        // When
        when(accountRepository.findByClientIdentification("123456")).thenReturn(Collections.emptyList());
        NotFoundException exception = assertThrows(NotFoundException.class, () -> adapter.findByClientIdentification("123456"));
        // Then
        assertEquals("Informacion no encontrada", exception.getMessage());
    }

    @Test
    void accountNumber_ShouldGenerateNumberInRange() {
        // Given
        Long result = adapter.accountNumber();
        // Then
        assertTrue(result >= 100000 && result <= 999999);
    }

    @Test
    void updateAccount_ShouldThrow_WhenBalanceNonZero() {
        // Given
        TransactionEntity tx = new TransactionEntity();
        tx.setBalance(50.0);
        accountEntity.setTransactionEntities(List.of(tx));
        // When
        when(accountRepository.findTopByAccountNumberAndStatusTrueOrderByTransactionEntitiesDateDesc(123L))
                .thenReturn(Optional.of(accountEntity));
        NotFoundException ex = assertThrows(NotFoundException.class, () -> adapter.updateAccount(123L));
        // Then
        assertEquals("No se puedo cambiar de estado, la cuenta tiene dinero", ex.getMessage());
    }

    @Test
    void updateAccount_ShouldThrow_WhenNoTransactions() {
        // Given
        accountEntity.setTransactionEntities(Collections.emptyList());
        // When
        when(accountRepository.findTopByAccountNumberAndStatusTrueOrderByTransactionEntitiesDateDesc(123L))
                .thenReturn(Optional.of(accountEntity));
        // Then
        assertThrows(NotFoundException.class, () -> adapter.updateAccount(123L));
    }

    @Test
    void updateAccount_ShouldThrow_WhenNotFound() {
        // When
        when(accountRepository.findTopByAccountNumberAndStatusTrueOrderByTransactionEntitiesDateDesc(123L))
                .thenReturn(Optional.empty());
        // Then
        assertThrows(NotFoundException.class, () -> adapter.updateAccount(123L));
    }

    @Test
    void updateAccount_ShouldDeactivate_WhenBalanceZero() {
        // Given
        TransactionEntity transaction = new TransactionEntity();
        transaction.setBalance(0.0);
        List<TransactionEntity> txs = List.of(transaction);
        accountEntity.setTransactionEntities(txs);
        when(accountRepository.findTopByAccountNumberAndStatusTrueOrderByTransactionEntitiesDateDesc(123L))
                .thenReturn(Optional.of(accountEntity));
        when(accountRepository.save(any())).thenReturn(accountEntity);
        // When
        Account updated = adapter.updateAccount(123L);
        // Then
        assertFalse(updated.getStatus());
    }
}
