package com.ms.accounttransactions_back.mapper.impl;

import com.ms.accounttransactions_back.dto.*;
import com.ms.accounttransactions_back.mapper.TransactionMapper;
import com.ms.accounttransactions_back.model.entity.Transaction;
import org.springframework.stereotype.Component;

/**
 * @author : Freddy Torres
 * file :  TransactionMapperImpl
 * @since : 4/4/2025, vie
 **/

@Component
public class TransactionMapperImpl implements TransactionMapper {
    @Override
    public Transaction toEntity(TransactionDto transactionDto) {
        if (transactionDto == null) return null;
        var transaction = new Transaction();
        transaction.setAmount(transactionDto.getAmount());
        transaction.setBalance(transactionDto.getBalance());
        transaction.setDate(transactionDto.getDate());
        transaction.setDescription(transactionDto.getDescription().toUpperCase());
        transaction.setTransactionType(transactionDto.getTransactionType());
        transaction.setAccount(transactionDto.getAccount());
        return transaction;
    }

    @Override
    public TransactionByAccountNumberDto toDto(Transaction transaction) {
        if (transaction == null) return null;
        TransactionByAccountNumberDto TransactionByAccountNumberDto = new TransactionByAccountNumberDto();
        TransactionByAccountNumberDto.setTransactionId(transaction.getTransactionId());
        TransactionByAccountNumberDto.setDescription(transaction.getDescription());
        TransactionByAccountNumberDto.setDate(transaction.getDate());
        TransactionByAccountNumberDto.setTransactionType(transaction.getTransactionType());
        TransactionByAccountNumberDto.setAmount(transaction.getAmount());
        TransactionByAccountNumberDto.setBalance(transaction.getBalance());
        return TransactionByAccountNumberDto;
    }

    @Override
    public ReportDto toReportDto(Transaction transaction) {
        if (transaction == null) return null;
        ReportDto reportDto = new ReportDto();
        reportDto.setTransactionId(transaction.getTransactionId());
        reportDto.setDescription(transaction.getDescription());
        reportDto.setDate(transaction.getDate());
        reportDto.setTransactionType(transaction.getTransactionType());
        reportDto.setAmount(transaction.getAmount());
        reportDto.setBalance(transaction.getBalance());
        reportDto.setAccount(transaction.getAccount());
        return reportDto;
    }


}
