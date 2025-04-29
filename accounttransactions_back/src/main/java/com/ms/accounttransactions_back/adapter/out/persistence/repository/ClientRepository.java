package com.ms.accounttransactions_back.adapter.out.persistence.repository;


import com.ms.accounttransactions_back.adapter.out.persistence.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author : Freddy Torres
 * file : ClientRepository
 * @since : 24/4/2025, jue
 **/
public interface ClientRepository extends JpaRepository<ClientEntity, Long> {
    Optional<ClientEntity> findAllByIdentificationAndStatusTrue(String identification);
}
