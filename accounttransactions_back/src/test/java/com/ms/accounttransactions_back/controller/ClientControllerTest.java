package com.ms.accounttransactions_back.controller;

import com.ms.accounttransactions_back.adapter.in.web.controller.ClientController;
import com.ms.accounttransactions_back.adapter.in.web.dto.ApiResponse;
import com.ms.accounttransactions_back.application.service.ClientService;
import com.ms.accounttransactions_back.domain.Client;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.springframework.http.HttpStatus;


/**
 * @author : Freddy Torres
 * file :  ClientControllerTest
 * @since : 3/4/2025, jue
 **/

class ClientControllerTest {

    private static final  String IDENTIFICACION = "1856893683";
    private static final  Long ID = 1L;

    Client clientDto;

    @Mock
    private AutoCloseable closeable;

    @InjectMocks
    private ClientController clientController;

    @Mock
    private ClientService clientService;


    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        clientDto = new Client();
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
        ResponseEntity<ApiResponse<Client>> responseEntity = clientController.getClientByIdentification(IDENTIFICACION);
        // Then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void postClient()  {
        // Given
        var client = new Client();
        // When
        ResponseEntity<ApiResponse<Client>> responseEntity = clientController.postClient(client);
        // Then
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    void patchClient()  {
        // When
        ResponseEntity<ApiResponse<Client>> responseEntity = clientController.patchClient(ID, clientDto);
        // Then
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }
}
