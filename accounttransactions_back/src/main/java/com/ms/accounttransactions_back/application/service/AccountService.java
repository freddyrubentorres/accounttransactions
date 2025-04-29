package com.ms.accounttransactions_back.application.service;

import com.ms.accounttransactions_back.application.port.in.AccountPort;
import com.ms.accounttransactions_back.application.port.out.LoadAccountPort;
import com.ms.accounttransactions_back.common.component.UseCase;
import com.ms.accounttransactions_back.domain.Account;
import lombok.AllArgsConstructor;

import java.util.List;

/**
 * @author : Freddy Torres
 * file : AccountService
 * @since : 25/4/2025, vie
 **/

@UseCase
@AllArgsConstructor
public class AccountService implements AccountPort {
    private final LoadAccountPort loadAccountPort;

    @Override
    public Account saveAccount(Account account) {
        return loadAccountPort.saveAccount(account);
    }

    @Override
    public List<Account> findByClientIdentification(String identification) {
        return loadAccountPort.findByClientIdentification(identification);
    }

    @Override
    public Account updateAccount( Long accountNumber) {
        return loadAccountPort.updateAccount(accountNumber);
    }
}
