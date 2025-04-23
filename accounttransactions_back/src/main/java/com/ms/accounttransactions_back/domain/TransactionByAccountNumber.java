package com.ms.accounttransactions_back.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ms.accounttransactions_back.adapter.out.persistence.entity.Account;
import com.ms.accounttransactions_back.domain.enums.TransactionType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * @author : Freddy Torres
 * file :  TransactionByAccountNumberDto
 * @since : 4/4/2025, vie
 **/

@Setter
@Getter
@ToString
public class TransactionByAccountNumber {
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
