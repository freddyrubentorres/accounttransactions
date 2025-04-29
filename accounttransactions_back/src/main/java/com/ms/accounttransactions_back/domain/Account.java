package com.ms.accounttransactions_back.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ms.accounttransactions_back.adapter.out.persistence.entity.ClientEntity;
import com.ms.accounttransactions_back.domain.enums.AccountType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import lombok.AllArgsConstructor;
import lombok.Data;
import jakarta.validation.constraints.NotNull;

/**
 * @author : Freddy Torres
 * file : Account
 * @since : 25/4/2025, vie
 **/

@Data
@AllArgsConstructor
public class Account {
    private Long accountId;
    private Long accountNumber;

    @NotNull(message = "{message.domain.person.account.accountType.NotNull}")
    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @NotNull(message = "{message.domain.person.account.initialBalance.NotNull}")
    @DecimalMin(value = "10.00", inclusive = true, message = "{message.domain.person.account.initialBalance.DecimalMin}")
    @DecimalMax(value = "1000000.00", inclusive = true, message = "{message.domain.person.account.initialBalance.DecimalMax}")
    private Double initialBalance;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull(message = "{message.domain.person.account.clientEntity.NotBlank}")
    private ClientEntity client;

    private Boolean status;

}
