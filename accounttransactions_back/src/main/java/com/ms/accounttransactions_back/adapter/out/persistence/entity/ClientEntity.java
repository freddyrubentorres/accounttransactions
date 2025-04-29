package com.ms.accounttransactions_back.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


/**
 * @author : Freddy Torres
 * file : ClientEntity
 * @since : 24/4/2025, jue
 **/


@Entity
@Table(name = "client", uniqueConstraints = {
        @UniqueConstraint(name = "uk_client_email", columnNames = {"email"})
})
@PrimaryKeyJoinColumn(name = "person_id")
@Getter
@Setter
public class ClientEntity extends PersonEntity {

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AccountEntity> accounts = new ArrayList<>();

}
