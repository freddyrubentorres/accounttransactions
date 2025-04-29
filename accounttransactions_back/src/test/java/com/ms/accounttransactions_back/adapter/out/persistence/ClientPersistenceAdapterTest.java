package com.ms.accounttransactions_back.adapter.out.persistence;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.ms.accounttransactions_back.adapter.out.persistence.entity.ClientEntity;
import com.ms.accounttransactions_back.application.exception.NotFoundException;
import com.ms.accounttransactions_back.domain.Client;
import com.ms.accounttransactions_back.domain.enums.Gender;
import com.ms.accounttransactions_back.adapter.out.persistence.repository.ClientRepository;
import com.ms.accounttransactions_back.domain.validator.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class ClientPersistenceAdapterTest {
    @Mock
    private ClientRepository clientRepository;
    private static final String IDENTIFICATION = "1749375738";
    @Mock
    private Validator validator;

    @InjectMocks
    private ClientPersistenceAdapter clientPersistenceAdapter;

    private Client client;
    private ClientEntity existingClient;

    @BeforeEach
    void setUp() {
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
        existingClient = new ClientEntity();
    }

    @Test
    void testFindAllByIdentificationAndStatusTrue_ShouldThrowNotFoundExceptionWhenClientNotFound() {
        // When
        when(clientRepository.findAllByIdentificationAndStatusTrue("123")).thenReturn(Optional.empty());
        // Then
        NotFoundException thrown = assertThrows(NotFoundException.class, () -> clientPersistenceAdapter.findAllByIdentificationAndStatusTrue("123"));
        assertEquals("Informacion no encontrada", thrown.getMessage());
    }

    @Test
    void testUpdateClient_ShouldThrowNotFoundExceptionIfClientNotFound() {
        // When
        when(clientRepository.findAllByIdentificationAndStatusTrue("123")).thenReturn(Optional.empty());
        // Then
        NotFoundException thrown = assertThrows(NotFoundException.class, () -> clientPersistenceAdapter.updateClient("123", client));
        assertEquals("Informacion no encontrada", thrown.getMessage());
    }

    @Test
    void testSaveClient_ShouldSaveAndReturnClient() {
        // When
        when(clientRepository.save(any(ClientEntity.class))).thenReturn(new ClientEntity());
        // Then
        Client result = clientPersistenceAdapter.saveClient(client);
        assertNotNull(result);
        verify(clientRepository).save(any(ClientEntity.class));
    }

    @Test
    void testUpdateClient_ShouldUpdateAndReturnUpdatedClient() {
        // When
        when(clientRepository.findAllByIdentificationAndStatusTrue("123")).thenReturn(Optional.of(existingClient));
        when(clientRepository.save(any(ClientEntity.class))).thenReturn(existingClient);
        doNothing().when(validator).validateEmail(anyString());
        doNothing().when(validator).validatePassword(anyString());
        doNothing().when(validator).validateName(anyString());
        doNothing().when(validator).validateLastName(anyString());
        doNothing().when(validator).validateGender(any());
        doNothing().when(validator).validateAge(anyInt());
        doNothing().when(validator).validateAddress(anyString());
        doNothing().when(validator).validatePhone(anyString());
        Client updatedClient = clientPersistenceAdapter.updateClient("123", client);
        // Then
        assertNotNull(updatedClient);
        verify(validator).validateEmail(anyString());
        verify(validator).validatePassword(anyString());
        verify(validator).validateName(anyString());
        verify(validator).validateLastName(anyString());
        verify(validator).validateGender(any());
        verify(validator).validateAge(anyInt());
        verify(validator).validateAddress(anyString());
        verify(validator).validatePhone(anyString());
    }
}
