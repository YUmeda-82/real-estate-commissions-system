package com.agency.commission.dto;

import com.agency.commission.domain.Broker;

public record BrokerResponseDTO(
    Long id,
    String name,
    String email,
    boolean active
) {
    public BrokerResponseDTO(Broker broker) {
        this(
            broker.getId(),
            broker.getName(),
            broker.getEmail(),
            broker.getActive()
        );
    }
}