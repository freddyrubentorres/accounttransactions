package com.ms.accounttransactions_back.service.impl;

import com.ms.accounttransactions_back.constants.ProcessConstants;
import com.ms.accounttransactions_back.dto.AccountByAccountNumberDto;
import com.ms.accounttransactions_back.dto.AccountDto;
import com.ms.accounttransactions_back.exception.NotFoundException;
import com.ms.accounttransactions_back.mapper.AccountMapper;
import com.ms.accounttransactions_back.model.entity.Account;
import com.ms.accounttransactions_back.model.entity.Transaction;
import com.ms.accounttransactions_back.model.enums.Status;
import com.ms.accounttransactions_back.model.enums.TransactionType;
import com.ms.accounttransactions_back.repository.AccountRepository;
import com.ms.accounttransactions_back.repository.ClientRepository;
import com.ms.accounttransactions_back.repository.TransactionRepository;
import com.ms.accounttransactions_back.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

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

    @Value("${message.entity.no.found}")
    public String ENTITY_NO_FOUND;

    @Value("${message.account.status.change.restricted}")
    public String ACCOUNT_STATUS_CHANGE_RESTRICTED;

    private final AccountRepository accountRepository;
    private final ClientRepository clientRepository;
    private final TransactionRepository transactionRepository;
    private final AccountMapper accountMapper;

    @Override
    @Transactional
    public AccountDto saveAccount(AccountDto accountDto) throws Exception {
        var client = clientRepository.findByClientId(accountDto.getClient().getClientId())
                .orElseThrow(() -> new NotFoundException(ENTITY_NO_FOUND));
        accountDto.setAccountNumber(accountNumber());
        accountDto.setClient(client);
        accountDto.setStatus(Status.TRUE);
        var savedAccount = accountRepository.save(accountMapper.toEntity(accountDto));
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
    public AccountByAccountNumberDto getAccountByAccountNumber(Long accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber)
                .map(accountMapper::toAccountNumberDto)
                .orElseThrow(() -> new NotFoundException(ENTITY_NO_FOUND));
    }

    @Override
    @Transactional(readOnly = true)
    public List<AccountByAccountNumberDto> getAccountByAccountNumber(String identification) {
        var accounts = accountRepository.findByClient_Identification(identification);
        if (accounts.isEmpty()) throw new  NotFoundException(ENTITY_NO_FOUND);
        return accounts.stream()
                .map(accountMapper::toAccountNumberDto)
                .collect(Collectors.toList());
    }

    @Override
    public AccountByAccountNumberDto putAccountByAccountNumber(Long accountNumber) throws Exception {
       var transaction = transactionRepository.findTop1ByAccount_AccountNumberOrderByDateDesc(accountNumber);
       if (transaction == null) throw new  NotFoundException(ENTITY_NO_FOUND);
       else if(transaction.getBalance()<=0) throw new  NotFoundException(ACCOUNT_STATUS_CHANGE_RESTRICTED);
        Account account= transaction.getAccount();
        account.setStatus(Status.FALSE);
        transaction.setAccount(account);
        transactionRepository.save(transaction);
        return accountMapper.toAccountNumberDto(transaction.getAccount());
    }

    public Long accountNumber(){
        var random = new Random();
        return 100000L + random.nextInt(900000);
    }
}
