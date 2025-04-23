package com.ms.accounttransactions_back.application.service.impl;

import com.ms.accounttransactions_back.domain.Client;
import com.ms.accounttransactions_back.adapter.in.web.exception.NotFoundException;
import com.ms.accounttransactions_back.adapter.out.persistence.mapper.ClientMapper;
import com.ms.accounttransactions_back.domain.enums.Status;
import com.ms.accounttransactions_back.adapter.out.persistence.repository.ClientRepository;
import com.ms.accounttransactions_back.application.service.ClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author : Freddy Torres
 * file :  ClientServiceImpl
 * @since : 3/4/2025, jue
 **/

@Slf4j
@Service
@RequiredArgsConstructor
@PropertySource("classpath:messages.properties")
public class ClientServiceImpl implements ClientService {

    @Value("${message.no.found}")
    public String noFound;

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    @Override
    @Transactional
    public Client saveClient(Client client){
        client.setClientId(getNextClientId());
        client.setStatus(Status.TRUE);
        client.setPassword(client.getPassword());
        var savedClient = clientRepository.save(clientMapper.toEntity(client));
        return clientMapper.toDto(savedClient);
    }

    @Override
    @Transactional(readOnly = true)
    public Client getClientByIdentification(String identification) {
        return clientRepository.findByIdentification(identification)
                .map(clientMapper::toDto)
                .orElseThrow(() -> new NotFoundException(noFound));
    }

    @Override
    @Transactional
    public Client updateClient(Long id, Client client)  {
        com.ms.accounttransactions_back.adapter.out.persistence.entity.Client existingClient = clientRepository.findByClientId(id)
                .orElseThrow(() -> new NotFoundException(noFound));
        if (client.getEmail() != null) existingClient.setEmail(client.getEmail());
        if (client.getPassword() != null) existingClient.setPassword(client.getPassword());
        if (client.getStatus() != null) existingClient.setStatus(client.getStatus());
        if (client.getName() != null) existingClient.setName(client.getName());
        if (client.getLastName() != null) existingClient.setLastName(client.getLastName());
        if (client.getGender() != null) existingClient.setGender(client.getGender());
        if (client.getAge() != null) existingClient.setAge(client.getAge());
        if (client.getIdentification() != null) existingClient.setIdentification(client.getIdentification());
        if (client.getAddress() != null) existingClient.setAddress(client.getAddress());
        if (client.getPhone() != null) existingClient.setPhone(client.getPhone());
        com.ms.accounttransactions_back.adapter.out.persistence.entity.Client savedClient = clientRepository.save(existingClient);
        return clientMapper.toDto(savedClient);
    }

    public Long getNextClientId() {
        var maxClientId = clientRepository.findMaxClientId();
        return maxClientId + 1;
    }
}
