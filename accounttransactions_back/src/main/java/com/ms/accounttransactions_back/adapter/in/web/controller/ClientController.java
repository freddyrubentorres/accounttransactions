package com.ms.accounttransactions_back.adapter.in.web.controller;

import com.ms.accounttransactions_back.domain.Client;
import com.ms.accounttransactions_back.adapter.in.web.dto.ApiResponse;
import com.ms.accounttransactions_back.adapter.in.web.dto.ResponseUtil;
import com.ms.accounttransactions_back.application.service.ClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;

/**
 * @author : Freddy Torres
 * file :  ClientController
 * @since : 3/4/2025, jue
 **/


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("${endpoint.clients.base}")
public class ClientController {

    @Value("${controller.ok}")
    public String mensaje;

    private final ClientService clientService;

    @PostMapping
    public ResponseEntity<ApiResponse<Client>> postClient(@Valid @RequestBody Client client) {
        var clientDto = clientService.saveClient(client);
        return new ResponseEntity<>(ResponseUtil.createSuccessResponse(mensaje, clientDto), HttpStatus.CREATED);
    }

    @GetMapping("/identification/{identification}")
    public ResponseEntity<ApiResponse<Client>> getClientByIdentification(@PathVariable String identification) {
        var clientDto = clientService.getClientByIdentification(identification);
        return ResponseEntity.ok(ResponseUtil.createSuccessResponse(mensaje, clientDto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<Client>> patchClient(@PathVariable Long id, @RequestBody Client client) {
        var updatedClient = clientService.updateClient(id, client);
        return new ResponseEntity<>(ResponseUtil.createSuccessResponse(mensaje, updatedClient), HttpStatus.CREATED);
    }

}
