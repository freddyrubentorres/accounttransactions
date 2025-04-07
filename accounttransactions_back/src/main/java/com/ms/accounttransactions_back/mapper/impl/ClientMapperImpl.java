package com.ms.accounttransactions_back.mapper.impl;

import com.ms.accounttransactions_back.dto.ClientDto;
import com.ms.accounttransactions_back.mapper.ClientMapper;
import com.ms.accounttransactions_back.model.entity.Client;
import org.springframework.stereotype.Component;

/**
 * @author : Freddy Torres
 * file :  ClientMapperImpl
 * @since : 3/4/2025, jue
 **/

@Component
public class ClientMapperImpl implements ClientMapper {
    @Override
    public ClientDto toDto(Client client) {
        if (client == null) return null;
        var clientDto= new ClientDto();
        clientDto.setPersonId(client.getPersonId());
        clientDto.setName(client.getName());
        clientDto.setLast_name(client.getLast_name());
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
    public Client toEntity(ClientDto clientDto) {
        if (clientDto == null) return null;
        var client = new Client();
        client.setClientId(clientDto.getClientId());
        client.setAge(clientDto.getAge());
        client.setEmail(clientDto.getEmail().toLowerCase());
        client.setPassword(clientDto.getPassword());
        client.setStatus(clientDto.getStatus());
        client.setName(clientDto.getName().toUpperCase());
        client.setLast_name(clientDto.getLast_name().toUpperCase());
        client.setGender(clientDto.getGender());
        client.setIdentification(clientDto.getIdentification());
        client.setAddress(clientDto.getAddress().toUpperCase());
        client.setPhone(clientDto.getPhone());
        return client;
    }
}
