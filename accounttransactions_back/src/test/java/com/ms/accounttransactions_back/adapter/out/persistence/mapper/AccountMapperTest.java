package com.ms.accounttransactions_back.adapter.out.persistence.mapper;

import com.ms.accounttransactions_back.adapter.out.persistence.entity.AccountEntity;
import com.ms.accounttransactions_back.adapter.out.persistence.entity.ClientEntity;
import com.ms.accounttransactions_back.domain.Account;
import com.ms.accounttransactions_back.domain.enums.AccountType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountMapperTest {
    @Test
    void testDomainToEntity_ShouldMapCorrectly() {
        // Given
        Account account = new Account(
                1L,123456L, AccountType.AHORRO,200.00,new ClientEntity(),true);
        // When
        AccountEntity entity = AccountMapper.domainToEntity(account);
        // Then
        assertNotNull(entity);
        assertEquals(account.getStatus(), entity.getStatus());
        assertEquals(account.getAccountNumber(), entity.getAccountNumber());
        assertEquals(account.getAccountType(), entity.getAccountType());
        assertEquals(account.getInitialBalance(), entity.getInitialBalance());
        assertEquals(account.getClient(), entity.getClient());
    }

    @Test
    void testEntityToDomain_ShouldMapCorrectly() {
        // Given
        AccountEntity entity = new AccountEntity();
        entity.setAccountId(1L);
        entity.setAccountNumber(123L);
        entity.setInitialBalance(1000.0);
        entity.setStatus(true);
        // When
        Account account = AccountMapper.entityToDomain(entity);
        // Then
        assertNotNull(account);
        assertEquals(entity.getAccountId(), account.getAccountId());
        assertEquals(entity.getAccountNumber(), account.getAccountNumber());
        assertEquals(entity.getAccountType(), account.getAccountType());
        assertEquals(entity.getInitialBalance(), account.getInitialBalance());
        assertEquals(entity.getClient(), account.getClient());
        assertEquals(entity.getStatus(), account.getStatus());
    }

    @Test
    void testEntityToDomain_ShouldReturnNullIfEntityIsNull() {
        // When
        AccountMapper.entityToDomain(null);
        // Then
        assertNull(null);
    }
}
