package com.ms.accounttransactions_back.adapter.out.persistence.mapper.impl;
import com.ms.accounttransactions_back.domain.AccountByAccountNumber;
import com.ms.accounttransactions_back.domain.Account;
import com.ms.accounttransactions_back.adapter.out.persistence.mapper.AccountMapper;
import org.springframework.stereotype.Component;

/**
 * @author : Freddy Torres
 * file :  AccountMapperImpl
 * @since : 4/4/2025, vie
 **/

@Component
public class AccountMapperImpl implements AccountMapper {
    @Override
    public Account toDto(com.ms.accounttransactions_back.adapter.out.persistence.entity.Account account) {
        if (account == null) return null;
        var accountDto= new Account();
        accountDto.setAccountId(account.getAccountId());
        accountDto.setAccountNumber(account.getAccountNumber());
        accountDto.setAccountType(account.getAccountType());
        accountDto.setInitialBalance(account.getInitialBalance());
        accountDto.setStatus(account.getStatus());
        accountDto.setClient(account.getClient());
        return accountDto;
    }

    @Override
    public AccountByAccountNumber toAccountNumberDto(com.ms.accounttransactions_back.adapter.out.persistence.entity.Account account) {
        if (account == null) return null;
        var accountByAccountNumberDto= new AccountByAccountNumber();
        accountByAccountNumberDto.setAccountId(account.getAccountId());
        accountByAccountNumberDto.setAccountNumber(account.getAccountNumber());
        accountByAccountNumberDto.setAccountType(account.getAccountType());
        accountByAccountNumberDto.setInitialBalance(account.getInitialBalance());
        accountByAccountNumberDto.setStatus(account.getStatus());
        return accountByAccountNumberDto;
    }

    @Override
    public com.ms.accounttransactions_back.adapter.out.persistence.entity.Account toEntity(Account accountDto) {
        if (accountDto == null) return null;
        var account = new com.ms.accounttransactions_back.adapter.out.persistence.entity.Account();
        account.setClient(accountDto.getClient());
        account.setAccountNumber(accountDto.getAccountNumber());
        account.setAccountType(accountDto.getAccountType());
        account.setInitialBalance(accountDto.getInitialBalance());
        account.setStatus(accountDto.getStatus());
        return account;
    }
}
