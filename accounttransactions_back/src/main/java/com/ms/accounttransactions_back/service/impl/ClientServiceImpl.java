package com.ms.accounttransactions_back.service.impl;

import com.ms.accounttransactions_back.common.AES256EncryptionService;
import com.ms.accounttransactions_back.dto.ClientDto;
import com.ms.accounttransactions_back.exception.NotFoundException;
import com.ms.accounttransactions_back.mapper.ClientMapper;
import com.ms.accounttransactions_back.model.entity.Client;
import com.ms.accounttransactions_back.model.enums.Status;
import com.ms.accounttransactions_back.repository.ClientRepository;
import com.ms.accounttransactions_back.service.ClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Value;

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

    @Value("${message.entity.no.found}")
    public String ENTITY_NO_FOUND;

    private final ClientRepository clientRepository;
    private final AES256EncryptionService encryptionService;
    private final ClientMapper clientMapper;

    @Override
    @Transactional
    public ClientDto saveClient(ClientDto clientDto) throws Exception {
        clientDto.setClientId(getNextClientId());
        clientDto.setStatus(Status.TRUE);
        clientDto.setPassword(encryptionService.encrypt(clientDto.getPassword()));
        var savedClient = clientRepository.save(clientMapper.toEntity(clientDto));
        return clientMapper.toDto(savedClient);
    }

    @Override
    @Transactional(readOnly = true)
    public ClientDto getClientByIdentification(String identification) {
        return clientRepository.findByIdentification(identification)
                .map(clientMapper::toDto)
                .orElseThrow(() -> new NotFoundException(ENTITY_NO_FOUND));
    }

    @Override
    @Transactional
    public ClientDto updateClient(Long id, ClientDto client) throws Exception {
        Client existingClient = clientRepository.findByClientId(id)
                .orElseThrow(() -> new NotFoundException(ENTITY_NO_FOUND));
        if (client.getEmail() != null) existingClient.setEmail(client.getEmail());
        if (client.getPassword() != null) existingClient.setPassword(encryptionService.encrypt(client.getPassword()));
        if (client.getStatus() != null) existingClient.setStatus(client.getStatus());
        if (client.getName() != null) existingClient.setName(client.getName());
        if (client.getLast_name() != null) existingClient.setLast_name(client.getLast_name());
        if (client.getGender() != null) existingClient.setGender(client.getGender());
        if (client.getAge() != null) existingClient.setAge(client.getAge());
        if (client.getIdentification() != null) existingClient.setIdentification(client.getIdentification());
        if (client.getAddress() != null) existingClient.setAddress(client.getAddress());
        if (client.getPhone() != null) existingClient.setPhone(client.getPhone());
        Client savedClient = clientRepository.save(existingClient);
        return clientMapper.toDto(savedClient);
    }

    public Long getNextClientId() {
        var maxClientId = clientRepository.findMaxClientId();
        return maxClientId + 1;
    }
}
