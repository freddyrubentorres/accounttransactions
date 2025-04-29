package com.ms.accounttransactions_back.application.port.out;


import com.ms.accounttransactions_back.domain.Client;

/**
 * @author : Freddy Torres
 * file : LoadClientPort
 * @since : 24/4/2025, jue
 **/
public interface LoadClientPort {
    Client findAllByIdentificationAndStatusTrue(String identification);

    Client saveClient(Client client);

    Client updateClient(String identification, Client client);
}
