package com.ms.accounttransactions_back.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ms.accounttransactions_back.adapter.out.persistence.entity.Account;
import com.ms.accounttransactions_back.domain.enums.TransactionType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author : Freddy Torres
 * file :  ReportDto
 * @since : 5/4/2025, sáb
 **/

@Getter
@Setter
@NoArgsConstructor
public class Report {
    private Long transactionId;
    private String description;
    private LocalDateTime date;
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;
    private Double amount;
    private Double balance;
    @JsonIgnore
    private Account account;
}
