package com.ms.accounttransactions_back.application.service;

import com.ms.accounttransactions_back.application.port.out.LoadClientPort;
import com.ms.accounttransactions_back.domain.Client;
import com.ms.accounttransactions_back.domain.enums.Gender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class ClientServiceTest {
    private LoadClientPort loadClientPort;
    private ClientService clientService;
    private Client client;
    private static final String IDENTIFICATION = "1749375738";

    @BeforeEach
    void setUp() {
        loadClientPort = mock(LoadClientPort.class);
        clientService = new ClientService(loadClientPort);
        client = new Client();
        client.setPhone("0993572274");
        client.setLastName("lasName");
        client.setAge(30);
        client.setAddress("address");
        client.setName("name");
        client.setGender(Gender.M);
        client.setPassword("password");
        client.setIdentification(IDENTIFICATION);
        client.setEmail("email@hotmail.com");
        client.setStatus(true);
    }

    @Test
    void shouldReturnFindAllByIdentificationAndStatusTrue() {
        // When
        when(loadClientPort.findAllByIdentificationAndStatusTrue("1845789457")).thenReturn(client);
        Client result = clientService.findAllByIdentificationAndStatusTrue("1845789457");
        // Then
        assertNotNull(result);
        verify(loadClientPort, times(1)).findAllByIdentificationAndStatusTrue("1845789457");
    }

    @Test
    void shouldSaveClient() {
        // When
        when(loadClientPort.saveClient(client)).thenReturn(client);
        Client result = clientService.saveClient(client);
        // Then
        assertNotNull(result);
        verify(loadClientPort, times(1)).saveClient(client);
    }

    @Test
    void shouldUpdateClient() {
        // When
        when(loadClientPort.updateClient("1845789457", client)).thenReturn(client);
        Client result = clientService.updateClient("1845789457", client);
        // Then
        assertNotNull(result);
        verify(loadClientPort, times(1)).updateClient("1845789457", client);
    }
}
