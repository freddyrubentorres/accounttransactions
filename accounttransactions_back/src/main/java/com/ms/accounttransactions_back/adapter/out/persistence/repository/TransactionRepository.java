package com.ms.accounttransactions_back.adapter.out.persistence.repository;

import com.ms.accounttransactions_back.adapter.out.persistence.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

/**
 * @author : Freddy Torres
 * file :  TransactionRepository
 * @since : 4/4/2025, vie
 **/

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Transaction findTop1ByAccount_AccountNumberOrderByDateDesc(@Param("accountNumber") Long accountNumber);

    @Query(value = "SELECT " +
            "C.TRANSACTION_ID AS TRANSACTION_ID, " +
            "B.ACCOUNT_ID AS ACCOUNT_ID, " +
            "C.TRANSACTION_TYPE AS TRANSACTION_TYPE, " +
            "CONCAT(A.NAME, ' ', A.LAST_NAME) AS NAME, " +
            "B.ACCOUNT_NUMBER AS ACCOUNTNUMBER, " +
            "B.ACCOUNT_TYPE AS ACCOUNTTYPE, " +
            "C.DATE AS DATE, " +
            "C.TRANSACTION_TYPE AS TRANSACTIONTYPE, " +
            "C.DESCRIPTION AS DESCRIPTION, " +
            "C.AMOUNT AS AMOUNT, " +
            "C.BALANCE AS BALANCE " +
            "FROM PERSON A " +
            "INNER JOIN ACCOUNT B ON (A.PERSON_ID = B.CLIENT_ID) " +
            "INNER JOIN TRANSACTION C ON (B.ACCOUNT_ID = C.ACCOUNT_ID) " +
            "WHERE A.IDENTIFICATION = :identification " +
            "AND C.DATE BETWEEN :startDate AND :endDate " +
            "ORDER BY B.ACCOUNT_NUMBER, C.DATE DESC", nativeQuery = true)
    List<Transaction> findTransactionsByClientIdentificationAndDateRange(
            String identification,
            LocalDate startDate,
            LocalDate endDate);
}
