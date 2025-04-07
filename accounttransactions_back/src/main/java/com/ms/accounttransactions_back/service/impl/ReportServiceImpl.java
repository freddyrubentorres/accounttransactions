package com.ms.accounttransactions_back.service.impl;

import com.ms.accounttransactions_back.dto.ReportDto;
import com.ms.accounttransactions_back.exception.NotFoundException;
import com.ms.accounttransactions_back.mapper.TransactionMapper;
import com.ms.accounttransactions_back.model.entity.Transaction;
import com.ms.accounttransactions_back.repository.TransactionRepository;
import com.ms.accounttransactions_back.service.ReportService;
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

    @Value("${message.entity.no.found}")
    public String ENTITY_NO_FOUND;

    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    @Transactional(readOnly = true)
    public Map<Long, List<ReportDto>> getTransactionsByIdentificationAndDate(String identification, String startDate, String endDate) {
        var transactions = transactionRepository.findTransactionsByClientIdentificationAndDateRange(identification, LocalDate.parse(startDate, formatter), LocalDate.parse(endDate, formatter));
        if (transactions.isEmpty()) throw new NotFoundException(ENTITY_NO_FOUND);
        Map<Long, List<Transaction>> groupedTransactions = transactions.stream()
                .collect(Collectors.groupingBy(transaction -> transaction.getAccount().getAccountNumber()));
        return groupedTransactions.entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> entry.getKey(),
                        entry -> entry.getValue().stream()
                                .map(transaction -> transactionMapper.toReportDto(transaction))
                                .collect(Collectors.toList())
                ));
    }
}
