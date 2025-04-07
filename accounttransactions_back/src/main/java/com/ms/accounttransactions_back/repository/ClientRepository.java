package com.ms.accounttransactions_back.repository;

import com.ms.accounttransactions_back.model.entity.Client;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author : Freddy Torres
 * file :  ClientRepository
 * @since : 3/4/2025, jue
 **/

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    @Query("SELECT COALESCE(MAX(c.clientId), 0) FROM Client c")
    Long findMaxClientId();
    Optional<Client> findByIdentification(String identification);
    Optional<Client> findByClientId(Long clientId);
}
