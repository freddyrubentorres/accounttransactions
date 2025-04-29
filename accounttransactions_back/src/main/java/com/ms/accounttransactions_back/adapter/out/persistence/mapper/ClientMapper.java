package com.ms.accounttransactions_back.adapter.out.persistence.mapper;

import com.ms.accounttransactions_back.adapter.out.persistence.entity.ClientEntity;
import com.ms.accounttransactions_back.domain.Client;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author : Freddy Torres
 * file : ClientMapper
 * @since : 24/4/2025, jue
 **/

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ClientMapper {

    public static ClientEntity domainToEntity(Client client) {
        ClientEntity entity = new ClientEntity();
        entity.setStatus(true);
        entity.setEmail(client.getEmail());
        entity.setPassword(client.getPassword());
        entity.setName(client.getName().toUpperCase());
        entity.setLastName(client.getLastName().toUpperCase());
        entity.setGender(client.getGender());
        entity.setAge(client.getAge());
        entity.setIdentification(client.getIdentification());
        entity.setAddress(client.getAddress().toUpperCase());
        entity.setPhone(client.getPhone().toUpperCase());
        return entity;
    }

    public static Client entityToDomain(ClientEntity entity) {
        if (entity == null) return null;
        var client = new Client();
        client.setEmail(entity.getEmail());
        client.setPassword(entity.getPassword());
        client.setGender(entity.getGender());
        client.setIdentification(entity.getIdentification());
        client.setAge(entity.getAge());
        client.setAddress(entity.getAddress());
        client.setName(entity.getName());
        client.setLastName(entity.getLastName());
        client.setPhone(entity.getPhone());
        client.setStatus(entity.getStatus());
        return client;
    }
}