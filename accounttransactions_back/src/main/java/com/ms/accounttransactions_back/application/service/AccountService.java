package com.ms.accounttransactions_back.application.service;

import com.ms.accounttransactions_back.domain.AccountByAccountNumber;
import com.ms.accounttransactions_back.domain.Account;

import java.util.List;

/**
 * @author : Freddy Torres
 * file :  AccountService
 * @since : 4/4/2025, vie
 **/

public interface AccountService {
    Account saveAccount(Account account);
    AccountByAccountNumber getAccountByAccountNumber(Long accountNumber);
    List<AccountByAccountNumber> getAccountByAccountNumber(String identification);
    AccountByAccountNumber putAccountByAccountNumber(Long accountNumber);
}
