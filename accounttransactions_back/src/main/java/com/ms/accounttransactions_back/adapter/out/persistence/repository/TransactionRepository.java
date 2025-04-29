package com.ms.accounttransactions_back.adapter.out.persistence.repository;

import com.ms.accounttransactions_back.adapter.out.persistence.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * @author : Freddy Torres
 * file : TransactionEntityRepository
 * @since : 25/4/2025, vie
 **/
public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {
    Optional<TransactionEntity> findTopByAccount_AccountNumberOrderByDateDesc(Long accountNumber);

    @Query("SELECT t,a FROM TransactionEntity t  " +
            "JOIN t.account a " +
            "JOIN a.client c " +
            "WHERE c.identification = :identification " +
            "AND t.date BETWEEN :startDate AND :endDate " +
            "ORDER BY a.accountNumber, t.date DESC")
    List<TransactionEntity> findTransactionsByIdentificationAndDateRange(
            @Param("identification") String identification,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate);
}
