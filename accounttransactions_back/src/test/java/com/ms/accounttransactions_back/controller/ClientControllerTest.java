package com.ms.accounttransactions_back.controller;

import com.ms.accounttransactions_back.dto.ClientDto;
import com.ms.accounttransactions_back.dto.response.ApiResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import com.ms.accounttransactions_back.service.ClientService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.springframework.http.HttpStatus;


/**
 * @author : Freddy Torres
 * file :  ClientControllerTest
 * @since : 3/4/2025, jue
 **/

class ClientControllerTest {

    private final static String IDENTIFICACION = "1856893683";
    private final static Long ID = 1L;

    ClientDto clientDto;

    @Mock
    private AutoCloseable closeable;

    @InjectMocks
    private ClientController clientController;

    @Mock
    private ClientService clientService;


    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        clientDto = new ClientDto();
    }

    @AfterEach
    void closeService() throws Exception {
        closeable.close();
    }

    @Test
    void getClientByIdentification() {
        // Given
        when(clientService.getClientByIdentification(IDENTIFICACION)).thenReturn(clientDto);
        // When
        ResponseEntity<ApiResponse<ClientDto>> responseEntity = clientController.getClientByIdentification(IDENTIFICACION);
        // Then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void postClient() throws Exception {
        // Given
        var client = new ClientDto();
        // When
        ResponseEntity<ApiResponse<ClientDto>> responseEntity = clientController.postClient(client);
        // Then
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    void patchClient() throws Exception {
        // When
        ResponseEntity<ApiResponse<ClientDto>> responseEntity = clientController.patchClient(ID, clientDto);
        // Then
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }
}
