package com.ms.accounttransactions_back.adapter.out.persistence.entity;

import com.ms.accounttransactions_back.domain.enums.AccountType;
import com.ms.accounttransactions_back.domain.enums.Status;
import jakarta.persistence.*;
import lombok.*;

/**
 * @author : Freddy Torres
 * file :  Account
 * @since : 4/4/2025, vie
 **/

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "account", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"accountNumber"})
})
public class Account {

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
    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id")
    private Client client;
}
    