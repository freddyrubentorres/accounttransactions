package com.ms.accounttransactions_back.adapter.out.persistence;

import com.ms.accounttransactions_back.adapter.out.persistence.mapper.TransactionMapper;
import com.ms.accounttransactions_back.adapter.out.persistence.repository.TransactionRepository;
import com.ms.accounttransactions_back.application.exception.NotFoundException;
import com.ms.accounttransactions_back.application.port.out.LoadTransactionPort;
import com.ms.accounttransactions_back.common.component.PersistenceAdapter;
import com.ms.accounttransactions_back.domain.Report;
import com.ms.accounttransactions_back.domain.Transaction;
import com.ms.accounttransactions_back.domain.enums.TransactionType;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author : Freddy Torres
 * file : TransactionPersistenceAdapter
 * @since : 25/4/2025, vie
 **/

@PersistenceAdapter
@AllArgsConstructor
public class TransactionPersistenceAdapter implements LoadTransactionPort {
    private static final String NOT_FOUND_MESSAGE = "Informacion no encontrada";
    private final TransactionRepository transactionRepository;
    public static final String MSG_INSUFFICIENT_BALANCE = "Saldo no disponible.";

    @Override
    public Transaction saveTransaction(Transaction transaction) {
        var existingAccount = transactionRepository.findTopByAccount_AccountNumberOrderByDateDesc(transaction.getAccount().getAccountNumber())
                .orElseThrow(() -> new NotFoundException(NOT_FOUND_MESSAGE));
        if (transaction.getAmount() < 0 && existingAccount.getBalance() < Math.abs(transaction.getAmount()))
            throw new NotFoundException(MSG_INSUFFICIENT_BALANCE);
        transaction.setDate(LocalDateTime.now());
        transaction.setTransactionType(transaction.getAmount() > 0 ? TransactionType.DEPOSITO : TransactionType.DEBITO);
        transaction.setBalance(transaction.getAmount() + existingAccount.getBalance());
        transaction.setAccount(existingAccount.getAccount());
        return TransactionMapper.entityToDomain(transactionRepository.save(TransactionMapper.domainToEntity(transaction)));
    }

    @Override
    public Map<Long, List<Report>> findTransactionsByIdentificationAndDateRange(String identification, String startDate, String endDate) {
        String startDateWithTime = startDate + "T00:00:00";
        String endDateWithTime = endDate + "T23:00:00";
        LocalDateTime start = LocalDateTime.parse(startDateWithTime);
        LocalDateTime end = LocalDateTime.parse(endDateWithTime);
        var report = transactionRepository.findTransactionsByIdentificationAndDateRange(identification, start, end);
        if (report.isEmpty()) throw new NotFoundException(NOT_FOUND_MESSAGE);
        var groupedTransactions = report.stream().collect(Collectors.groupingBy(transaction -> transaction.getAccount().getAccountNumber()));
        return groupedTransactions.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey,
                        entry -> entry.getValue().stream()
                                .map(TransactionMapper::entityToReport)
                                .toList()));
    }
}
