package com.ms.accounttransactions_back.adapter.out.persistence.mapper.impl;

import com.ms.accounttransactions_back.domain.Report;
import com.ms.accounttransactions_back.domain.TransactionByAccountNumber;
import com.ms.accounttransactions_back.domain.Transaction;
import com.ms.accounttransactions_back.adapter.out.persistence.mapper.TransactionMapper;
import org.springframework.stereotype.Component;

/**
 * @author : Freddy Torres
 * file :  TransactionMapperImpl
 * @since : 4/4/2025, vie
 **/

@Component
public class TransactionMapperImpl implements TransactionMapper {
    @Override
    public com.ms.accounttransactions_back.adapter.out.persistence.entity.Transaction toEntity(Transaction transactionDto) {
        if (transactionDto == null) return null;
        var transaction = new com.ms.accounttransactions_back.adapter.out.persistence.entity.Transaction();
        transaction.setAmount(transactionDto.getAmount());
        transaction.setBalance(transactionDto.getBalance());
        transaction.setDate(transactionDto.getDate());
        transaction.setDescription(transactionDto.getDescription().toUpperCase());
        transaction.setTransactionType(transactionDto.getTransactionType());
        transaction.setAccount(transactionDto.getAccount());
        return transaction;
    }

    @Override
    public TransactionByAccountNumber toDto(com.ms.accounttransactions_back.adapter.out.persistence.entity.Transaction transaction) {
        if (transaction == null) return null;
        var transactionByAccountNumber = new TransactionByAccountNumber();
        transactionByAccountNumber.setTransactionId(transaction.getTransactionId());
        transactionByAccountNumber.setDescription(transaction.getDescription());
        transactionByAccountNumber.setDate(transaction.getDate());
        transactionByAccountNumber.setTransactionType(transaction.getTransactionType());
        transactionByAccountNumber.setAmount(transaction.getAmount());
        transactionByAccountNumber.setBalance(transaction.getBalance());
        return transactionByAccountNumber;
    }

    @Override
    public Report toReportDto(com.ms.accounttransactions_back.adapter.out.persistence.entity.Transaction transaction) {
        if (transaction == null) return null;
        var report = new Report();
        report.setTransactionId(transaction.getTransactionId());
        report.setDescription(transaction.getDescription());
        report.setDate(transaction.getDate());
        report.setTransactionType(transaction.getTransactionType());
        report.setAmount(transaction.getAmount());
        report.setBalance(transaction.getBalance());
        report.setAccount(transaction.getAccount());
        return report;
    }


}
