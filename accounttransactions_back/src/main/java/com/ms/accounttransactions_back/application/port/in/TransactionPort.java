package com.ms.accounttransactions_back.application.port.in;

import com.ms.accounttransactions_back.domain.Report;
import com.ms.accounttransactions_back.domain.Transaction;

import java.util.List;
import java.util.Map;

/**
 * @author : Freddy Torres
 * file : TransactionPort
 * @since : 25/4/2025, vie
 **/
public interface TransactionPort {
    Transaction saveTransaction(Transaction transaction);

    Map<Long, List<Report>> findTransactionsByIdentificationAndDateRange(String identification, String startDate, String endDate);
}
