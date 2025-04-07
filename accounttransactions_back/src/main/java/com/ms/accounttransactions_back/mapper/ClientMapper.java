package com.ms.accounttransactions_back.mapper;

import com.ms.accounttransactions_back.dto.ClientDto;
import com.ms.accounttransactions_back.model.entity.Client;
import org.mapstruct.Mapper;

/**
 * @author : Freddy Torres
 * file :  ClientMapper
 * @since : 3/4/2025, jue
 **/

@Mapper(componentModel = "spring")
public interface ClientMapper {
    ClientDto toDto(Client client);
    Client toEntity(ClientDto clientDto);
}
