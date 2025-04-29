package com.ms.accounttransactions_back.adapter.out.persistence.mapper;

import com.ms.accounttransactions_back.adapter.out.persistence.entity.TransactionEntity;
import com.ms.accounttransactions_back.domain.Report;
import com.ms.accounttransactions_back.domain.Transaction;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author : Freddy Torres
 * file : TransactionMapper
 * @since : 25/4/2025, vie
 **/

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TransactionMapper {

    public static TransactionEntity domainToEntity(Transaction transaction) {
        var entity = new TransactionEntity();
        entity.setBalance(transaction.getBalance());
        entity.setDate(transaction.getDate());
        entity.setAmount(transaction.getAmount());
        entity.setDescription(transaction.getDescription());
        entity.setTransactionType(transaction.getTransactionType());
        entity.setAccount(transaction.getAccount());
        return entity;
    }

    public static Transaction entityToDomain(TransactionEntity entity) {
        var transaction = new Transaction();
        transaction.setAccount(entity.getAccount());
        transaction.setTransactionType(entity.getTransactionType());
        transaction.setDescription(entity.getDescription());
        transaction.setDate(entity.getDate());
        transaction.setBalance(entity.getBalance());
        transaction.setAmount(entity.getAmount());
        return transaction;
    }

    public static Report entityToReport(TransactionEntity entity) {
        if (entity == null) return null;
        Report report= new Report();
        report.setAmount(entity.getAmount());
        report.setDescription(entity.getDescription());
        report.setDate(entity.getDate());
        report.setBalance(entity.getBalance());
        report.setTransactionType(entity.getTransactionType());
        return report;
    }
}
