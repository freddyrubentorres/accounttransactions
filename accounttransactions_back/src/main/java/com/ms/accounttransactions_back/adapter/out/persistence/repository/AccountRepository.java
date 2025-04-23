package com.ms.accounttransactions_back.adapter.out.persistence.repository;

import com.ms.accounttransactions_back.adapter.out.persistence.entity.Account;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author : Freddy Torres
 * file :  AccountRepository
 * @since : 4/4/2025, vie
 **/

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByAccountNumber(Long accountNumber);
    List<Account> findByClient_Identification(String identification);
}
