package com.ms.accounttransactions_back.adapter.out.persistence.mapper;

import com.ms.accounttransactions_back.adapter.out.persistence.entity.AccountEntity;
import com.ms.accounttransactions_back.domain.Account;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author : Freddy Torres
 * file : AccountMapper
 * @since : 25/4/2025, vie
 **/

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AccountMapper {
    public static AccountEntity domainToEntity(Account account) {
        AccountEntity entity = new AccountEntity();
        entity.setStatus(account.getStatus());
        entity.setAccountNumber(account.getAccountNumber());
        entity.setAccountType(account.getAccountType());
        entity.setInitialBalance(account.getInitialBalance());
        entity.setClient(account.getClient());
        return entity;
    }

    public static Account entityToDomain(AccountEntity entity) {
        if (entity == null) return null;
        return new Account(
                entity.getAccountId(),
                entity.getAccountNumber(),
                entity.getAccountType(),
                entity.getInitialBalance(),
                entity.getClient(),
                entity.getStatus()
        );
    }
}
