package com.ms.accounttransactions_back.adapter.out.persistence.entity;


import com.ms.accounttransactions_back.domain.enums.AccountType;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


/**
 * @author : Freddy Torres
 * file : AccountEntity
 * @since : 25/4/2025, vie
 **/

@Entity
@Table(name = "account", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"accountNumber"})
})
@Data
public class AccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Long accountId;

    @Column(name = "account_number")
    private Long accountNumber;

    @Column(name = "account_type")
    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @Column(name = "initial_balance")
    private Double initialBalance;

    @Column(name = "status")
    private Boolean status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", referencedColumnName = "person_id")
    private ClientEntity client;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TransactionEntity> transactionEntities = new ArrayList<>();
}
