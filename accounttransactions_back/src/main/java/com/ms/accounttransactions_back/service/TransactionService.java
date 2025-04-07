package com.ms.accounttransactions_back.service;

import com.ms.accounttransactions_back.dto.TransactionByAccountNumberDto;
import com.ms.accounttransactions_back.dto.TransactionDto;

/**
 * @author : Freddy Torres
 * file :  TransactionService
 * @since : 4/4/2025, vie
 **/

public interface TransactionService {
    TransactionByAccountNumberDto saveTransaction(TransactionDto transactionDto) throws Exception;
}
