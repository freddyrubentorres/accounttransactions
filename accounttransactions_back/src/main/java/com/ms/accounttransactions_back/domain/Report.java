package com.ms.accounttransactions_back.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ms.accounttransactions_back.domain.enums.TransactionType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author : Freddy Torres
 * file : Report
 * @since : 25/4/2025, vie
 **/
@Data
public class Report {
  private String description;
  private LocalDateTime date;
  @Enumerated(EnumType.STRING)
  private TransactionType transactionType;
  private Double amount;
  private Double balance;
  @JsonIgnore
  private Account account;
}
