package com.ms.accounttransactions_back.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ms.accounttransactions_back.model.entity.Account;
import com.ms.accounttransactions_back.model.enums.TransactionType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import java.time.LocalDateTime;

/**
 * @author : Freddy Torres
 * file :  TransactionDto
 * @since : 4/4/2025, vie
 **/

@Setter
@Getter
@ToString
public class TransactionDto {

    private Long transactionId;

    @NotBlank(message = "{message.dto.TransactionDto.description.NotNull}")
    @Size(min = 5, max = 25, message = "{message.dto.TransactionDto.description.Size}")
    private String description;

    @JsonIgnore
    private LocalDateTime date;

    @JsonIgnore
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    @NotNull(message = "{message.dto.TransactionDto.amount.NotNull}")
    @DecimalMin(value = "-1000000.00", inclusive = true, message = "{message.dto.TransactionDto.amount.DecimalMin}")
    @DecimalMax(value = "1000000.00", inclusive = true, message = "{message.dto.TransactionDto.amount.DecimalMax}")
    private Double amount;

    @JsonIgnore
    private Double balance;

    @NotNull(message = "{message.dto.TransactionDto.account.NotNull}")
    private Account account;
}
