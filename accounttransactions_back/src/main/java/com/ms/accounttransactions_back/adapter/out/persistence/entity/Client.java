package com.ms.accounttransactions_back.adapter.out.persistence.entity;

import com.ms.accounttransactions_back.domain.enums.Status;
import jakarta.persistence.*;
import lombok.*;

/**
 * @author : Freddy Torres
 * file :  Client
 * @since : 3/4/2025, jue
 **/

@Entity
@Table(name = "client", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"email"})
})
@PrimaryKeyJoinColumn(name = "person_id")
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class Client extends Person {

    @Column(name = "client_id", unique = true, nullable = false)
    private Long clientId;

    @Column(name = "email",unique=true)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

}
