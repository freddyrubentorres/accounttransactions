package com.ms.accounttransactions_back.application.service;

import com.ms.accounttransactions_back.domain.TransactionByAccountNumber;
import com.ms.accounttransactions_back.domain.Transaction;

/**
 * @author : Freddy Torres
 * file :  TransactionService
 * @since : 4/4/2025, vie
 **/

public interface TransactionService {
    TransactionByAccountNumber saveTransaction(Transaction transaction);
}
