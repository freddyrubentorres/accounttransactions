package com.ms.accounttransactions_back.adapter.out.persistence.entity;

import com.ms.accounttransactions_back.domain.enums.Gender;
import jakarta.persistence.*;
import lombok.Data;

/**
 * @author : Freddy Torres
 * file : PersonEntity
 * @since : 24/4/2025, jue
 **/
@Data
@Entity
@Table(name = "person", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"identification"}, name = "uk_person_identification")
})
@Inheritance(strategy = InheritanceType.JOINED)
public class PersonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")
    private Long personId;

    @Column(name = "name")
    private String name;

    @Column(name = "last_Name")
    private String lastName;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "age")
    private int age;

    @Column(name = "identification", unique = true)
    private String identification;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;

    @Column(name = "status")
    private Boolean status;
}