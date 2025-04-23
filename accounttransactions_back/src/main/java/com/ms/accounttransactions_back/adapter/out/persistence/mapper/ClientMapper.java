package com.ms.accounttransactions_back.adapter.out.persistence.mapper;

import com.ms.accounttransactions_back.domain.Client;
import org.mapstruct.Mapper;

/**
 * @author : Freddy Torres
 * file :  ClientMapper
 * @since : 3/4/2025, jue
 **/

@Mapper(componentModel = "spring")
public interface ClientMapper {
    Client toDto(com.ms.accounttransactions_back.adapter.out.persistence.entity.Client client);
    com.ms.accounttransactions_back.adapter.out.persistence.entity.Client toEntity(Client client);
}
