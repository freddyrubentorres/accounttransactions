package com.ms.accounttransactions_back.controller;

import com.ms.accounttransactions_back.constants.ProcessConstants;
import com.ms.accounttransactions_back.dto.ClientDto;
import com.ms.accounttransactions_back.dto.response.ApiResponse;
import com.ms.accounttransactions_back.dto.response.ResponseUtil;
import com.ms.accounttransactions_back.service.ClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.PropertySource;
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

@PropertySource("classpath:messages.properties")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(ProcessConstants.API_PATH_CLIENTS)
public class ClientController {

    @Value("${controller.ok}")
    public String CONTROLLER_OK;

    private final ClientService clientService;

    @PostMapping
    public ResponseEntity<ApiResponse<ClientDto>> postClient(@Valid @RequestBody ClientDto client) throws Exception {
        var clientDto = clientService.saveClient(client);
        return new ResponseEntity<>(ResponseUtil.createSuccessResponse(CONTROLLER_OK, clientDto), HttpStatus.CREATED);
    }

    @GetMapping(ProcessConstants.API_PATH_CLIENTS_BY_IDENTIFICACION)
    public ResponseEntity<ApiResponse<ClientDto>> getClientByIdentification(@PathVariable String identification) {
        var clientDto = clientService.getClientByIdentification(identification);
        return ResponseEntity.ok(ResponseUtil.createSuccessResponse(CONTROLLER_OK, clientDto));
    }

    @PatchMapping(ProcessConstants.API_PATH_CLIENTS_PATCH_ID)
    public ResponseEntity<ApiResponse<ClientDto>> patchClient(@PathVariable Long id, @RequestBody ClientDto clientDto) throws Exception  {
        var updatedClient = clientService.updateClient(id, clientDto);
        return new ResponseEntity<>(ResponseUtil.createSuccessResponse(CONTROLLER_OK, updatedClient), HttpStatus.CREATED);
    }

}
