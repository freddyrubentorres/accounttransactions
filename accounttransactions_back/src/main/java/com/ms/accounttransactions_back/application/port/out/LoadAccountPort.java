package com.ms.accounttransactions_back.application.port.out;

import com.ms.accounttransactions_back.domain.Account;

/**
 * @author : Freddy Torres
 * file : LoadAccountPort
 * @since : 25/4/2025, vie
 **/
public interface LoadAccountPort {
    Account saveAccount(Account account);
    Account findByClientIdentification(Long identification);
    Account updateAccount( Long accountNumber);
}
