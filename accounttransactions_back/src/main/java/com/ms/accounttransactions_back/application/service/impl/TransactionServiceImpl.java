package com.ms.accounttransactions_back.application.service.impl;

import com.ms.accounttransactions_back.adapter.in.web.constants.ProcessConstants;
import com.ms.accounttransactions_back.domain.TransactionByAccountNumber;
import com.ms.accounttransactions_back.domain.Transaction;
import com.ms.accounttransactions_back.adapter.in.web.exception.NotFoundException;
import com.ms.accounttransactions_back.adapter.out.persistence.mapper.TransactionMapper;
import com.ms.accounttransactions_back.domain.enums.TransactionType;
import com.ms.accounttransactions_back.adapter.out.persistence.repository.TransactionRepository;
import com.ms.accounttransactions_back.application.service.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * @author : Freddy Torres
 * file :  TransactionServiceImpl
 * @since : 4/4/2025, vie
 **/

@Slf4j
@Service
@RequiredArgsConstructor
@PropertySource("classpath:messages.properties")
public class TransactionServiceImpl implements TransactionService {

    @Value("${message.no.found}")
    public String noFound;

    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;

    @Override
    @Transactional
    public TransactionByAccountNumber saveTransaction(Transaction transactionDto)  {
        var transaction = transactionRepository.findTop1ByAccount_AccountNumberOrderByDateDesc(transactionDto.getAccount().getAccountNumber());
        if (transaction==null) throw new  NotFoundException(noFound);
        else if (transactionDto.getAmount()<0 && transaction.getBalance()<Math.abs(transactionDto.getAmount()))throw new  NotFoundException(ProcessConstants.MSG_INSUFFICIENT_BALANCE);
        transactionDto.setDate(LocalDateTime.now());
        transactionDto.setTransactionType(transactionDto.getAmount() > 0 ? TransactionType.DEPOSITO : TransactionType.DEBITO);
        transactionDto.setBalance(transactionDto.getAmount() > 0 ? transactionDto.getAmount()+transaction.getBalance() : transaction.getBalance()+transactionDto.getAmount());
        transactionDto.setAccount(transaction.getAccount());
        return transactionMapper.toDto(transactionRepository.save(transactionMapper.toEntity(transactionDto)));
    }
}
