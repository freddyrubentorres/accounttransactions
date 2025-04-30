package com.ms.accounttransactions_back.adapter.out.persistence.repository;

import com.ms.accounttransactions_back.adapter.out.persistence.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author : Freddy Torres
 * file : AccountRepository
 * @since : 25/4/2025, vie
 **/
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
    Optional<AccountEntity> findByAccountNumber(Long identification);
    Optional<AccountEntity> findTopByAccountNumberAndStatusTrueOrderByTransactionEntitiesDateDesc(Long accountNumber);

}
