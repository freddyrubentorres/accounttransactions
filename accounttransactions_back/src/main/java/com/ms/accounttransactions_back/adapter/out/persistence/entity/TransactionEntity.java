package com.ms.accounttransactions_back.adapter.out.persistence.entity;

import java.time.LocalDateTime;
import com.ms.accounttransactions_back.domain.enums.TransactionType;
import jakarta.persistence.*;
import lombok.Data;

/**
 * @author : Freddy Torres
 * file : Transaction
 * @since : 25/4/2025, vie
 **/

@Entity
@Table(name = "transaction")
@Data
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private Long transactionId;

    @Column(name = "description")
    private String description;

    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "transactionType")
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "balance")
    private Double balance;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "account_id", referencedColumnName = "account_id")
    private AccountEntity account;
}
