package com.ms.accounttransactions_back.service;

import com.ms.accounttransactions_back.dto.ClientDto;

/**
 * @author : Freddy Torres
 * file :  ClientService
 * @since : 3/4/2025, jue
 **/

public interface ClientService {
    ClientDto saveClient(ClientDto clientDto) throws Exception;
    ClientDto getClientByIdentification(String identification);
    ClientDto updateClient(Long id, ClientDto client) throws Exception;
}
