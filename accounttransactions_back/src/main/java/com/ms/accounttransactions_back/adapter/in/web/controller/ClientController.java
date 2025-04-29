package com.ms.accounttransactions_back.adapter.in.web.controller;

import com.ms.accounttransactions_back.adapter.in.web.dto.ApiResponse;
import com.ms.accounttransactions_back.adapter.in.web.util.ResponseUtil;
import com.ms.accounttransactions_back.application.port.in.ClientPort;
import com.ms.accounttransactions_back.domain.Client;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

/**
 * @author : Freddy Torres
 * file : ClientController
 * @since : 24/4/2025, jue
 **/

@RestController
@RequiredArgsConstructor
@RequestMapping("/clients")
public class ClientController {
    private final ClientPort clientPort;
    public static final String SUCCESS_MESSAGE = "controllerOk";

    @PostMapping
    public ResponseEntity<ApiResponse<Client>> postAccount(@Valid @RequestBody Client clientRequest) {
        var client = clientPort.saveClient(clientRequest);
        return ResponseEntity.ok(ResponseUtil.createSuccessResponse(SUCCESS_MESSAGE, client));
    }

    @GetMapping("/identification/{identification}")
    public ResponseEntity<ApiResponse<Client>> findAllByIdentificationAndStatusTrue(@PathVariable String identification) {
        var client = clientPort.findAllByIdentificationAndStatusTrue(identification);
        return ResponseEntity.ok(ResponseUtil.createSuccessResponse(SUCCESS_MESSAGE, client));
    }

    @PatchMapping("/identification/{identification}")
    public ResponseEntity<ApiResponse<Client>> patchAccount(@PathVariable String identification, @RequestBody Client clientRequest) {
        var client = clientPort.updateClient(identification, clientRequest);
        return ResponseEntity.ok(ResponseUtil.createSuccessResponse(SUCCESS_MESSAGE, client));
    }
}
