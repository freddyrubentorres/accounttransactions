package com.ms.accounttransactions_back.mapper;

import com.ms.accounttransactions_back.dto.ReportDto;
import com.ms.accounttransactions_back.dto.TransactionByAccountNumberDto;
import com.ms.accounttransactions_back.dto.TransactionDto;
import com.ms.accounttransactions_back.model.entity.Transaction;
import org.mapstruct.Mapper;

/**
 * @author : Freddy Torres
 * file :  TransactionMapper
 * @since : 4/4/2025, vie
 **/

@Mapper(componentModel = "spring")
public interface TransactionMapper {
    Transaction toEntity(TransactionDto transactionDto);
    TransactionByAccountNumberDto toDto(Transaction transaction);
    ReportDto toReportDto(Transaction transaction);
}
