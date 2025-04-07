package com.ms.accounttransactions_back.mapper.impl;
import com.ms.accounttransactions_back.dto.AccountByAccountNumberDto;
import com.ms.accounttransactions_back.dto.AccountDto;
import com.ms.accounttransactions_back.mapper.AccountMapper;
import com.ms.accounttransactions_back.model.entity.Account;
import org.springframework.stereotype.Component;

/**
 * @author : Freddy Torres
 * file :  AccountMapperImpl
 * @since : 4/4/2025, vie
 **/

@Component
public class AccountMapperImpl implements AccountMapper {
    @Override
    public AccountDto toDto(Account account) {
        if (account == null) return null;
        var accountDto= new AccountDto();
        accountDto.setAccountId(account.getAccountId());
        accountDto.setAccountNumber(account.getAccountNumber());
        accountDto.setAccountType(account.getAccountType());
        accountDto.setInitialBalance(account.getInitialBalance());
        accountDto.setStatus(account.getStatus());
        accountDto.setClient(account.getClient());
        return accountDto;
    }

    @Override
    public AccountByAccountNumberDto toAccountNumberDto(Account account) {
        if (account == null) return null;
        var accountByAccountNumberDto= new AccountByAccountNumberDto();
        accountByAccountNumberDto.setAccountId(account.getAccountId());
        accountByAccountNumberDto.setAccountNumber(account.getAccountNumber());
        accountByAccountNumberDto.setAccountType(account.getAccountType());
        accountByAccountNumberDto.setInitialBalance(account.getInitialBalance());
        accountByAccountNumberDto.setStatus(account.getStatus());
        return accountByAccountNumberDto;
    }

    @Override
    public Account toEntity(AccountDto accountDto) {
        if (accountDto == null) return null;
        var account = new Account();
        account.setClient(accountDto.getClient());
        account.setAccountNumber(accountDto.getAccountNumber());
        account.setAccountType(accountDto.getAccountType());
        account.setInitialBalance(accountDto.getInitialBalance());
        account.setStatus(accountDto.getStatus());
        return account;
    }
}
