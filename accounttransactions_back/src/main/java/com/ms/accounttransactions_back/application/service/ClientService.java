package com.ms.accounttransactions_back.application.service;


import com.ms.accounttransactions_back.application.port.in.ClientPort;
import com.ms.accounttransactions_back.application.port.out.LoadClientPort;
import com.ms.accounttransactions_back.common.component.UseCase;
import com.ms.accounttransactions_back.domain.Client;
import lombok.AllArgsConstructor;

/**
 * @author : Freddy Torres
 * file : ClientService
 * @since : 24/4/2025, jue
 **/

@UseCase
@AllArgsConstructor
public class ClientService implements ClientPort {
    private final LoadClientPort loadClientPort;

    @Override
    public Client findAllByIdentificationAndStatusTrue(String identification) {
        return loadClientPort.findAllByIdentificationAndStatusTrue(identification);
    }

    @Override
    public Client saveClient(Client client) {
        return loadClientPort.saveClient(client);
    }

    @Override
    public Client updateClient(String identification, Client client) {
        return loadClientPort.updateClient(identification,client);
    }
}
