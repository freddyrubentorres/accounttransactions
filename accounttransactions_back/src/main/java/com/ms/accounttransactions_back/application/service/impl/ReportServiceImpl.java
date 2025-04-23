package com.ms.accounttransactions_back.application.service.impl;

import com.ms.accounttransactions_back.domain.Report;
import com.ms.accounttransactions_back.adapter.in.web.exception.NotFoundException;
import com.ms.accounttransactions_back.adapter.out.persistence.mapper.TransactionMapper;
import com.ms.accounttransactions_back.adapter.out.persistence.entity.Transaction;
import com.ms.accounttransactions_back.adapter.out.persistence.repository.TransactionRepository;
import com.ms.accounttransactions_back.application.service.ReportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author : Freddy Torres
 * file :  ReportServiceImpl
 * @since : 5/4/2025, sáb
 **/

@Slf4j
@Service
@RequiredArgsConstructor
@PropertySource("classpath:messages.properties")
public class ReportServiceImpl implements ReportService {

    @Value("${message.no.found}")
    public String noFound;


    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    @Transactional(readOnly = true)
    public Map<Long, List<Report>> getTransactionsByIdentificationAndDate(String identification, String startDate, String endDate) {
        var transactions = transactionRepository.findTransactionsByClientIdentificationAndDateRange(identification, LocalDate.parse(startDate, formatter), LocalDate.parse(endDate, formatter));
        if (transactions.isEmpty()) throw new NotFoundException(noFound);
        Map<Long, List<Transaction>> groupedTransactions = transactions.stream()
                .collect(Collectors.groupingBy(transaction -> transaction.getAccount().getAccountNumber()));
        return groupedTransactions.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue().stream()
                                .map(transactionMapper::toReportDto)
                                .toList()
                ));
    }
}
