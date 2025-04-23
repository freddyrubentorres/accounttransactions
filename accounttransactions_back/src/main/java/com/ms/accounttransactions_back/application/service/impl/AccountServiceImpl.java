package com.ms.accounttransactions_back.application.service.impl;

import com.ms.accounttransactions_back.adapter.in.web.constants.ProcessConstants;
import com.ms.accounttransactions_back.domain.AccountByAccountNumber;
import com.ms.accounttransactions_back.domain.Account;
import com.ms.accounttransactions_back.adapter.in.web.exception.NotFoundException;
import com.ms.accounttransactions_back.adapter.out.persistence.mapper.AccountMapper;
import com.ms.accounttransactions_back.adapter.out.persistence.entity.Transaction;
import com.ms.accounttransactions_back.domain.enums.Status;
import com.ms.accounttransactions_back.domain.enums.TransactionType;
import com.ms.accounttransactions_back.adapter.out.persistence.repository.AccountRepository;
import com.ms.accounttransactions_back.adapter.out.persistence.repository.ClientRepository;
import com.ms.accounttransactions_back.adapter.out.persistence.repository.TransactionRepository;
import com.ms.accounttransactions_back.application.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author : Freddy Torres
 * file :  AccountServiceImpl
 * @since : 4/4/2025, vie
 **/

@Slf4j
@Service
@RequiredArgsConstructor
@PropertySource("classpath:messages.properties")
public class AccountServiceImpl implements AccountService {

   @Value("${message.no.found}")
    public String noFound;

   @Value("${message.account.status.change.restricted}")
    public String accountStatusChangeRestricted;

    private final AccountRepository accountRepository;
    private final ClientRepository clientRepository;
    private final TransactionRepository transactionRepository;
    private final AccountMapper accountMapper;

    private static final SecureRandom SECURE_RANDOM = new SecureRandom();

    @Override
    @Transactional
    public Account saveAccount(Account account) {
        var client = clientRepository.findByClientId(account.getClient().getClientId())
                .orElseThrow(() -> new NotFoundException(noFound));
        account.setAccountNumber(accountNumber());
        account.setClient(client);
        account.setStatus(Status.TRUE);
        var savedAccount = accountRepository.save(accountMapper.toEntity(account));
        if(savedAccount.getAccountId() != null){
            var transaction= new Transaction();
            transaction.setAmount(savedAccount.getInitialBalance());
            transaction.setBalance(savedAccount.getInitialBalance());
            transaction.setDate(LocalDateTime.now());
            transaction.setDescription(ProcessConstants.TRANSACTION_ACCOUNT_OPENING);
            transaction.setTransactionType(TransactionType.DEPOSITO);
            transaction.setAccount(savedAccount);
            transactionRepository.save(transaction);
        }
        return accountMapper.toDto(savedAccount);
    }

    @Override
    @Transactional(readOnly = true)
    public AccountByAccountNumber getAccountByAccountNumber(Long accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber)
                .map(accountMapper::toAccountNumberDto)
                .orElseThrow(() -> new NotFoundException(noFound));
    }

    @Override
    @Transactional(readOnly = true)
    public List<AccountByAccountNumber> getAccountByAccountNumber(String identification) {
        var accounts = accountRepository.findByClient_Identification(identification);
        if (accounts.isEmpty()) throw new  NotFoundException(noFound);
        return accounts.stream()
                .map(accountMapper::toAccountNumberDto)
                .toList();
    }

    @Override
    public AccountByAccountNumber putAccountByAccountNumber(Long accountNumber){
       var transaction = transactionRepository.findTop1ByAccount_AccountNumberOrderByDateDesc(accountNumber);
       if (transaction == null) throw new  NotFoundException(noFound);
       else if(transaction.getBalance()<=0) throw new  NotFoundException(accountStatusChangeRestricted);
        com.ms.accounttransactions_back.adapter.out.persistence.entity.Account account= transaction.getAccount();
        account.setStatus(Status.FALSE);
        transaction.setAccount(account);
        transactionRepository.save(transaction);
        return accountMapper.toAccountNumberDto(transaction.getAccount());
    }

    public Long accountNumber() {
        return 100000L + SECURE_RANDOM.nextInt(900000);
    }
}
