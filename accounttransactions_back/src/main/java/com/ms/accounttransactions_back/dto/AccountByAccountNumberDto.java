package com.ms.accounttransactions_back.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ms.accounttransactions_back.model.entity.Client;
import com.ms.accounttransactions_back.model.enums.AccountType;
import com.ms.accounttransactions_back.model.enums.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

/**
 * @author : Freddy Torres
 * file :  AccountByAccountNumberDto
 * @since : 4/4/2025, vie
 **/

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AccountByAccountNumberDto {
    private Long accountId;

    private Long accountNumber;

    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    private Double initialBalance;

    private Status status;

    @JsonIgnore
    private Client client;
}
