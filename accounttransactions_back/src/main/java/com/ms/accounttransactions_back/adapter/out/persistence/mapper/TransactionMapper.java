package com.ms.accounttransactions_back.adapter.out.persistence.mapper;

import com.ms.accounttransactions_back.domain.Report;
import com.ms.accounttransactions_back.domain.TransactionByAccountNumber;
import com.ms.accounttransactions_back.domain.Transaction;
import org.mapstruct.Mapper;

/**
 * @author : Freddy Torres
 * file :  TransactionMapper
 * @since : 4/4/2025, vie
 **/

@Mapper(componentModel = "spring")
public interface TransactionMapper {
    com.ms.accounttransactions_back.adapter.out.persistence.entity.Transaction toEntity(Transaction transaction);
    TransactionByAccountNumber toDto(com.ms.accounttransactions_back.adapter.out.persistence.entity.Transaction transaction);
    Report toReportDto(com.ms.accounttransactions_back.adapter.out.persistence.entity.Transaction transaction);
}
