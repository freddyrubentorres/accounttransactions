package com.ms.accounttransactions_back.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ms.accounttransactions_back.adapter.out.persistence.entity.AccountEntity;
import com.ms.accounttransactions_back.domain.enums.TransactionType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;

import java.time.LocalDateTime;

/**
 * @author : Freddy Torres
 * file : Transaction
 * @since : 25/4/2025, vie
 **/
@Data
public class Transaction {
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long transactionId;

    @NotBlank(message = "{message.domain.transaction.description.NotNull}")
    @Size(min = 5, max = 25, message = "{message.domain.transaction.description.Size}")
    private String description;

    @JsonIgnore
    private LocalDateTime date;

    @JsonIgnore
    private TransactionType transactionType;

    @NotNull(message = "{message.domain.transaction.amount.NotNull}")
    @DecimalMin(value = "-1000000.00", inclusive = true, message = "{message.domain.transaction.amount.DecimalMin}")
    @DecimalMax(value = "1000000.00", inclusive = true, message = "{message.domain.transaction.amount.DecimalMax}")
    private Double amount;

    @JsonIgnore
    private Double balance;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull(message = "{message.domain.transaction.accountEntity.NotNull}")
    private AccountEntity account;
}
