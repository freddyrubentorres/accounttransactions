package com.ms.accounttransactions_back.service;

import com.ms.accounttransactions_back.dto.AccountByAccountNumberDto;
import com.ms.accounttransactions_back.dto.AccountDto;

import java.util.List;

/**
 * @author : Freddy Torres
 * file :  AccountService
 * @since : 4/4/2025, vie
 **/

public interface AccountService {
    AccountDto saveAccount(AccountDto accountDto) throws Exception;
    AccountByAccountNumberDto getAccountByAccountNumber(Long accountNumber);
    List<AccountByAccountNumberDto> getAccountByAccountNumber(String identification);
    AccountByAccountNumberDto putAccountByAccountNumber(Long accountNumber) throws Exception;
}
