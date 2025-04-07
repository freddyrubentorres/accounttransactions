package com.ms.accounttransactions_back.dto;

import com.ms.accounttransactions_back.model.entity.Client;
import com.ms.accounttransactions_back.model.enums.AccountType;
import com.ms.accounttransactions_back.model.enums.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

/**
 * @author : Freddy Torres
 * file :  AccountDto
 * @since : 4/4/2025, vie
 **/

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {

    private Long accountId;

    private Long accountNumber;

    @NotNull(message = "{message.dto.AccountDto.accountType.NotNull}")
    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @NotNull(message = "{message.dto.AccountDto.initialBalance.NotNull}")
    @DecimalMin(value = "10.00", inclusive = true, message = "{message.dto.AccountDto.initialBalance.DecimalMin}")
    @DecimalMax(value = "1000000.00", inclusive = true, message = "{message.dto.AccountDto.initialBalance.DecimalMax}")
    private Double initialBalance;

    private Status status;

    @NotNull(message = "{message.dto.AccountDto.client.NotBlank}")
    private Client client;
}
