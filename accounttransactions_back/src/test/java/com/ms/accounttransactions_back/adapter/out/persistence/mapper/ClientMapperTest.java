package com.ms.accounttransactions_back.adapter.out.persistence.mapper;

import static org.junit.jupiter.api.Assertions.*;

import com.ms.accounttransactions_back.adapter.out.persistence.entity.ClientEntity;
import com.ms.accounttransactions_back.domain.Client;
import com.ms.accounttransactions_back.domain.enums.Gender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ClientMapperTest {
    private Client client;
    private ClientEntity clientEntity;
    private static final String IDENTIFICATION = "1749375738";
    @BeforeEach
    void setUp() {
        client = new Client();
        client.setPhone("0993572274");
        client.setLastName("Doe");
        client.setAge(30);
        client.setAddress("address");
        client.setName("JOHN");
        client.setGender(Gender.M);
        client.setPassword("password");
        client.setIdentification(IDENTIFICATION);
        client.setEmail("email@hotmail.com");
        client.setStatus(true);
        clientEntity = new ClientEntity();
        clientEntity.setStatus(true);
        clientEntity.setEmail("email@example.com");
        clientEntity.setPassword("password123");
        clientEntity.setName("John");
        clientEntity.setLastName("Doe");
        clientEntity.setGender(Gender.M);
        clientEntity.setAge(25);
        clientEntity.setIdentification("1749375738");
        clientEntity.setAddress("123 Main Street");
        clientEntity.setPhone("0993572274");
    }

    @Test
    void testDomainToEntity() {
        // When
        ClientEntity entity = ClientMapper.domainToEntity(client);
        // Then
        assertEquals("JOHN", entity.getName());
        assertEquals("DOE", entity.getLastName());
        assertEquals(client.getEmail(), entity.getEmail());
        assertEquals(client.getPassword(), entity.getPassword());
        assertEquals(client.getGender(), entity.getGender());
        assertEquals(client.getAge(), entity.getAge());
        assertEquals(client.getIdentification(), entity.getIdentification());
        assertEquals(client.getAddress().toUpperCase(), entity.getAddress());
        assertEquals(client.getPhone(), entity.getPhone());
        assertTrue(entity.getStatus());
    }

    @Test
    void testEntityToDomain() {
        // When
        Client domainClient = ClientMapper.entityToDomain(clientEntity);
        // Then
        assertEquals(clientEntity.getEmail(), domainClient.getEmail());
        assertEquals(clientEntity.getPassword(), domainClient.getPassword());
        assertEquals(clientEntity.getName(), domainClient.getName());
        assertEquals(clientEntity.getLastName(), domainClient.getLastName());
        assertEquals(clientEntity.getGender(), domainClient.getGender());
        assertEquals(clientEntity.getAge(), domainClient.getAge());
        assertEquals(clientEntity.getIdentification(), domainClient.getIdentification());
        assertEquals(clientEntity.getAddress(), domainClient.getAddress());
        assertEquals(clientEntity.getPhone(), domainClient.getPhone());
    }

    @Test
    void testEntityToDomainNull() {
        // When
        ClientMapper.entityToDomain(null);
        // Then
        assertNull(null);
    }
}
