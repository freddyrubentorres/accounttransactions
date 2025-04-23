package com.ms.accounttransactions_back.adapter.out.persistence.mapper.impl;

import com.ms.accounttransactions_back.domain.Client;
import com.ms.accounttransactions_back.adapter.out.persistence.mapper.ClientMapper;
import org.springframework.stereotype.Component;

/**
 * @author : Freddy Torres
 * file :  ClientMapperImpl
 * @since : 3/4/2025, jue
 **/

@Component
public class ClientMapperImpl implements ClientMapper {
    @Override
    public Client toDto(com.ms.accounttransactions_back.adapter.out.persistence.entity.Client client) {
        if (client == null) return null;
        var clientDto= new Client();
        clientDto.setPersonId(client.getPersonId());
        clientDto.setName(client.getName());
        clientDto.setLastName(client.getLastName());
        clientDto.setGender(client.getGender());
        clientDto.setAge(client.getAge());
        clientDto.setIdentification(client.getIdentification());
        clientDto.setAddress(client.getAddress());
        clientDto.setPhone(client.getPhone());
        clientDto.setClientId(client.getClientId());
        clientDto.setEmail(client.getEmail());
        clientDto.setPassword(client.getPassword());
        clientDto.setStatus(client.getStatus());
        return clientDto;
    }

    @Override
    public com.ms.accounttransactions_back.adapter.out.persistence.entity.Client toEntity(Client clientDto) {
        if (clientDto == null) return null;
        var client = new com.ms.accounttransactions_back.adapter.out.persistence.entity.Client();
        client.setClientId(clientDto.getClientId());
        client.setAge(clientDto.getAge());
        client.setEmail(clientDto.getEmail().toLowerCase());
        client.setPassword(clientDto.getPassword());
        client.setStatus(clientDto.getStatus());
        client.setName(clientDto.getName().toUpperCase());
        client.setLastName(clientDto.getLastName().toUpperCase());
        client.setGender(clientDto.getGender());
        client.setIdentification(clientDto.getIdentification());
        client.setAddress(clientDto.getAddress().toUpperCase());
        client.setPhone(clientDto.getPhone());
        return client;
    }
}
