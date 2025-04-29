package com.ms.accounttransactions_back.application.port.in;

import com.ms.accounttransactions_back.domain.Client;

/**
 * @author : Freddy Torres
 * file : ClientPort
 * @since : 24/4/2025, jue
 **/
public interface ClientPort {
    Client findAllByIdentificationAndStatusTrue(String identification);
    Client saveClient(Client client);
    Client updateClient(String identification,Client client);
}
