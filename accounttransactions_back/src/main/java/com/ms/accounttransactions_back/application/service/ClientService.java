package com.ms.accounttransactions_back.application.service;

import com.ms.accounttransactions_back.domain.Client;

/**
 * @author : Freddy Torres
 * file :  ClientService
 * @since : 3/4/2025, jue
 **/

public interface ClientService {
    Client saveClient(Client client) ;
    Client getClientByIdentification(String identification);
    Client updateClient(Long id, Client client);
}
