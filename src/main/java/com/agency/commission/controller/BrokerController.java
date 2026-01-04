package com.agency.commission.controller;

import com.agency.commission.dto.BrokerRequestDTO;
import com.agency.commission.dto.BrokerResponseDTO;
import com.agency.commission.service.BrokerService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/brokers")

public class BrokerController {
    
    private final BrokerService service;

    public BrokerController(BrokerService service){
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<BrokerResponseDTO> create(
        @RequestBody @Valid BrokerRequestDTO data,
        UriComponentsBuilder uriBuilder      
    ) {
        BrokerResponseDTO newBroker = service.create(data);

        URI uri = uriBuilder.path("/brokers/{id}").buildAndExpand(newBroker.id()).toUri();

        return ResponseEntity.created(uri).body(newBroker);
    }
}
