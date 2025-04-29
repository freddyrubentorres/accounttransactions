package com.ms.accounttransactions_back.application.service;

import com.ms.accounttransactions_back.application.port.in.TransactionPort;
import com.ms.accounttransactions_back.application.port.out.LoadTransactionPort;
import com.ms.accounttransactions_back.common.component.UseCase;
import com.ms.accounttransactions_back.domain.Report;
import com.ms.accounttransactions_back.domain.Transaction;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * @author : Freddy Torres
 * file : TransactionPortService
 * @since : 25/4/2025, vie
 **/

@UseCase
@AllArgsConstructor
public class TransactionService implements TransactionPort {
    private final LoadTransactionPort loadTransactionPort;

    @Override
    public Transaction saveTransaction(Transaction transaction) {
        return loadTransactionPort.saveTransaction(transaction);
    }

    @Override
    public Map<Long, List<Report>> findTransactionsByIdentificationAndDateRange(String identification, String startDate, String endDate) {
        return loadTransactionPort.findTransactionsByIdentificationAndDateRange(identification, startDate, endDate);
    }
}
