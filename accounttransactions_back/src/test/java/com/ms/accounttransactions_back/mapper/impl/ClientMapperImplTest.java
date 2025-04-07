package com.ms.accounttransactions_back.mapper.impl;

import com.ms.accounttransactions_back.dto.ClientDto;
import com.ms.accounttransactions_back.model.entity.Client;
import com.ms.accounttransactions_back.model.enums.Gender;
import com.ms.accounttransactions_back.model.enums.Status;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author : Freddy Torres
 * file :  ClientMapperImplTest
 * @since : 3/4/2025, jue
 **/

class ClientMapperImplTest {

    private ClientMapperImpl clientMapper;

    @Mock
    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        clientMapper = new ClientMapperImpl();
    }

    @AfterEach
    void closeService() throws Exception {
        closeable.close();
    }

    @Test
    void toDto_IsNull() {
        // Given
        var clientDto = clientMapper.toDto(null);
        // Then
        assertNull(clientDto);
    }

    @Test
    void toDto_NotNull() {
        // Given
        var client = mock(Client.class);
        when(client.getClientId()).thenReturn(1L);
        when(client.getEmail()).thenReturn("test@example.com");
        when(client.getPassword()).thenReturn("password123");
        when(client.getStatus()).thenReturn(Status.TRUE);
        when(client.getPersonId()).thenReturn(12345L);
        when(client.getName()).thenReturn("John");
        when(client.getLast_name()).thenReturn("Doe");
        when(client.getGender()).thenReturn(Gender.M);
        when(client.getAge()).thenReturn(30L);
        when(client.getIdentification()).thenReturn("ID12345");
        when(client.getAddress()).thenReturn("123 Main St");
        when(client.getPhone()).thenReturn("555-1234");
        // When
        var clientDto = clientMapper.toDto(client);
        // Then
        assertNotNull(clientDto);
        assertEquals(1L, clientDto.getClientId());
        assertEquals("test@example.com", clientDto.getEmail());
        assertEquals("password123", clientDto.getPassword());
        assertEquals(Status.TRUE, clientDto.getStatus());
        assertEquals(12345L, clientDto.getPersonId());
        assertEquals("John", clientDto.getName());
        assertEquals("Doe", clientDto.getLast_name());
        assertEquals(Gender.M, clientDto.getGender());
        assertEquals(30, clientDto.getAge());
        assertEquals("ID12345", clientDto.getIdentification());
        assertEquals("123 Main St", clientDto.getAddress());
        assertEquals("555-1234", clientDto.getPhone());
    }

    @Test
    void toEntityNull() {
        // Act
        var client = clientMapper.toEntity(null);
        // Assert
        assertNull(client);
    }

    @Test
    void toEntityNoNull() {
        // Given
        var clientDto = new ClientDto();
        clientDto.setClientId(1L);
        clientDto.setEmail("test@example.com");
        clientDto.setPassword("password123");
        clientDto.setStatus(Status.TRUE);
        clientDto.setPersonId(10L);
        clientDto.setName("JOHN");
        clientDto.setLast_name("DOE");
        clientDto.setGender(Gender.M);
        clientDto.setAge(30L);
        clientDto.setIdentification("ID12345");
        clientDto.setAddress("123 MAIN ST");
        clientDto.setPhone("123-456-7890");
        // When
        var client = clientMapper.toEntity(clientDto);
        // Then
        assertNotNull(client);
        assertEquals(clientDto.getClientId(), client.getClientId());
        assertEquals(clientDto.getEmail(), client.getEmail());
        assertEquals(clientDto.getPassword(), client.getPassword());
        assertEquals(clientDto.getStatus(), client.getStatus());
        assertEquals(clientDto.getName(), client.getName());
        assertEquals(clientDto.getLast_name(), client.getLast_name());
        assertEquals(clientDto.getGender(), client.getGender());
        assertEquals(clientDto.getAge(), client.getAge());
        assertEquals(clientDto.getIdentification(), client.getIdentification());
        assertEquals(clientDto.getAddress(), client.getAddress());
        assertEquals(clientDto.getPhone(), client.getPhone());
    }
}
