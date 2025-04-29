package com.ms.accounttransactions_back.adapter.in.web.controller;

import com.ms.accounttransactions_back.adapter.in.web.dto.ApiResponse;
import com.ms.accounttransactions_back.application.port.in.ClientPort;
import com.ms.accounttransactions_back.domain.Client;
import com.ms.accounttransactions_back.domain.enums.Gender;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@SpringBootTest
@ContextConfiguration(classes = EntityManager.class)
class ClientControllerTest {
    private static final String IDENTIFICATION = "1749375738";
    @InjectMocks
    private ClientController clientController;
    @Mock
    private ClientPort clientPort;
    private Client client;

    @BeforeEach
    void init() {
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
    void findAllByIdentificationAndStatusTrue() {
        // Given
        when(clientPort.findAllByIdentificationAndStatusTrue(IDENTIFICATION)).thenReturn(client);
        // When
        ResponseEntity<ApiResponse<Client>> responseEntity = clientController.findAllByIdentificationAndStatusTrue("1749375738");
        // Then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        ApiResponse<Client> responseBody = responseEntity.getBody();
        assertNotNull(responseBody);
        assertNotNull(responseBody.getData());
    }

    @Test
    void postAccount() {
        // Given
        when(clientPort.saveClient(client)).thenReturn(client);
        // When
        ResponseEntity<ApiResponse<Client>> responseEntity = clientController.postAccount(client);
        // Then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        ApiResponse<Client> responseBody = responseEntity.getBody();
        assertNotNull(responseBody);
        assertNotNull(responseBody.getData());
        assertEquals(client.getEmail(), responseBody.getData().getEmail());
        assertEquals(client.getPassword(), responseBody.getData().getPassword());
        assertEquals(client.getName(), responseBody.getData().getName());
        assertEquals(client.getLastName(), responseBody.getData().getLastName());
        assertEquals(client.getGender(), responseBody.getData().getGender());
        assertEquals(client.getAge(), responseBody.getData().getAge());
        assertEquals(client.getIdentification(), responseBody.getData().getIdentification());
        assertEquals(client.getAddress(), responseBody.getData().getAddress());
        assertEquals(client.getPhone(), responseBody.getData().getPhone());
    }

    @Test
    void patchAccount() {
        when(clientPort.updateClient(eq(IDENTIFICATION), any(Client.class))).thenReturn(client);
        // When
        ResponseEntity<ApiResponse<Client>> responseEntity = clientController.patchAccount(IDENTIFICATION, client);
        // Then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        ApiResponse<Client> responseBody = responseEntity.getBody();
        assertNotNull(responseBody);
        assertNotNull(responseBody.getData());
        assertEquals(client.getEmail(), responseBody.getData().getEmail());
        assertEquals(client.getPassword(), responseBody.getData().getPassword());
        assertEquals(client.getName(), responseBody.getData().getName());
        assertEquals(client.getLastName(), responseBody.getData().getLastName());
        assertEquals(client.getGender(), responseBody.getData().getGender());
        assertEquals(client.getAge(), responseBody.getData().getAge());
        assertEquals(client.getIdentification(), responseBody.getData().getIdentification());
        assertEquals(client.getAddress(), responseBody.getData().getAddress());
        assertEquals(client.getPhone(), responseBody.getData().getPhone());
    }
}
