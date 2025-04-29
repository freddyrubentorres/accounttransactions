package com.ms.accounttransactions_back.application.port.in;

import com.ms.accounttransactions_back.domain.Account;

import java.util.List;

/**
 * @author : Freddy Torres
 * file : AccountPort
 * @since : 25/4/2025, vie
 **/
public interface AccountPort {
    Account saveAccount(Account account);
    List<Account> findByClientIdentification(String identification);
    Account updateAccount(Long accountNumber);
}
