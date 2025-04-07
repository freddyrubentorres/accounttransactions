package com.ms.accounttransactions_back.service.impl;

import com.ms.accounttransactions_back.common.AES256EncryptionService;
import com.ms.accounttransactions_back.dto.ClientDto;
import com.ms.accounttransactions_back.exception.NotFoundException;
import com.ms.accounttransactions_back.mapper.ClientMapper;
import com.ms.accounttransactions_back.model.entity.Client;
import com.ms.accounttransactions_back.model.enums.Status;
import com.ms.accounttransactions_back.repository.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * @author : Freddy Torres
 * file :  ClientServiceImplTest
 * @since : 3/4/2025, jue
 **/

@ExtendWith(MockitoExtension.class)
class ClientServiceImplTest {

    private final static String IDENTIFICACION = "1737672847";


    ClientDto clientDto = new ClientDto();
    Client client = new Client();

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientServiceImpl clientService;

    @Mock
    private ClientMapper clientMapper;

    @Mock
    private AES256EncryptionService encryptionService;

    @BeforeEach
    void setUp() {
        clientDto = new ClientDto();
        clientDto.setClientId(1L);
        clientDto.setEmail("test@example.com");
        clientDto.setPassword("securePassword");
        clientDto.setStatus(Status.TRUE);
        client = new Client();
        client.setClientId(1L);
        client.setEmail("test@example.com");
        client.setPassword("securePassword");
        client.setStatus(Status.TRUE);
    }

    @Test
    public void saveClient() throws Exception {
        // When
        when(clientRepository.save(any(Client.class))).thenReturn(client);
        when(clientMapper.toEntity(any(ClientDto.class))).thenReturn(client);
        when(clientMapper.toDto(any(Client.class))).thenReturn(clientDto);
        when(encryptionService.encrypt(anyString())).thenReturn("encryptedPassword");
        var result = clientService.saveClient(clientDto);
        // Then
        assertNotNull(result);
        assertEquals(clientDto.getClientId(), result.getClientId());
        assertEquals(clientDto.getEmail(), result.getEmail());
        assertEquals(clientDto.getStatus(), result.getStatus());
        verify(clientRepository, times(1)).save(any(Client.class));
    }

    @Test
    void getClientByIdentification() {
        // When
        when(clientRepository.findByIdentification(IDENTIFICACION)).thenReturn(Optional.empty());
        // Then
        assertThrows(NotFoundException.class, () -> clientService.getClientByIdentification(IDENTIFICACION));
    }

    @Test
    void updateClientNoFound() {
        // Given
        var clientDtoUpdate = new ClientDto();
        // When
        when(clientRepository.findByClientId(1L)).thenReturn(Optional.empty());
        // Then
        assertThrows(NotFoundException.class, () -> clientService.updateClient(1L, clientDtoUpdate));
    }

    @Test
    void updateClient() throws Exception {
        // When
        when(clientRepository.findByClientId(1L)).thenReturn(Optional.of(client));
        when(clientRepository.save(any(Client.class))).thenReturn(client);
        when(clientMapper.toDto(client)).thenReturn(clientDto);
        var result = clientService.updateClient(1L, clientDto);
        // Then
        assertNotNull(result);
    }
}
