package com.ms.accounttransactions_back.adapter.out.persistence;

import com.ms.accounttransactions_back.adapter.out.persistence.mapper.ClientMapper;
import com.ms.accounttransactions_back.adapter.out.persistence.repository.ClientRepository;
import com.ms.accounttransactions_back.application.exception.NotFoundException;
import com.ms.accounttransactions_back.application.port.out.LoadClientPort;
import com.ms.accounttransactions_back.common.component.PersistenceAdapter;
import com.ms.accounttransactions_back.domain.Client;
import com.ms.accounttransactions_back.domain.validator.Validator;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author : Freddy Torres
 * file : ClientPersistenceAdapter
 * @since : 24/4/2025, jue
 **/

@PersistenceAdapter
@AllArgsConstructor
public class ClientPersistenceAdapter implements LoadClientPort {
    private final ClientRepository clientRepository;
    private static final String NOT_FOUND_MESSAGE = "Informacion no encontrada";
    private final Validator validator;

    @Override
    @Transactional(readOnly = true)
    public Client findAllByIdentificationAndStatusTrue(String identification) {
        return clientRepository.findAllByIdentificationAndStatusTrue(identification)
                .map(ClientMapper::entityToDomain)
                .orElseThrow(() -> new NotFoundException(NOT_FOUND_MESSAGE));
    }

    @Override
    @Transactional
    public Client saveClient(Client client) {
        return ClientMapper.entityToDomain(clientRepository.save(ClientMapper.domainToEntity(client)));
    }

    @Override
    @Transactional
    public Client updateClient(String identification, Client client) {
        var existingClient = clientRepository.findAllByIdentificationAndStatusTrue(identification)
                .orElseThrow(() -> new NotFoundException(NOT_FOUND_MESSAGE));
        if (client.getEmail() != null) {
            validator.validateEmail(client.getEmail());
            existingClient.setEmail(client.getEmail());
        }
        if (client.getPassword() != null) {
            validator.validatePassword(client.getPassword());
            existingClient.setPassword(client.getPassword());
        }
        if (client.getName() != null) {
            validator.validateName(client.getName());
            existingClient.setName(client.getName());
        }
        if (client.getLastName() != null) {
            validator.validateLastName(client.getLastName());
            existingClient.setLastName(client.getLastName());
        }
        if (client.getGender() != null) {
            validator.validateGender(client.getGender());
            existingClient.setGender(client.getGender());
        }
        if (client.getAge() != null) {
            validator.validateAge(client.getAge());
            existingClient.setAge(client.getAge());
        }
        if (client.getAddress() != null) {
            validator.validateAddress(client.getAddress());
            existingClient.setAddress(client.getAddress());
        }
        if (client.getPhone() != null) {
            validator.validatePhone(client.getPhone());
            existingClient.setPhone(client.getPhone());
        }
        return ClientMapper.entityToDomain(clientRepository.save(existingClient));
    }
}
