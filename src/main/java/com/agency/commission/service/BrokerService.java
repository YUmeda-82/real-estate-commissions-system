package com.agency.commission.service;

import com.agency.commission.domain.Broker;
import com.agency.commission.dto.BrokerRequestDTO;
import com.agency.commission.dto.BrokerResponseDTO;
import com.agency.commission.repository.BrokerRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service

public class BrokerService {
    private final BrokerRepository repository;

    public BrokerService(BrokerRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public BrokerResponseDTO create(BrokerRequestDTO data) {

        if (repository.existsByCpf(data.cpf())){
            throw new IllegalArgumentException("Erro: CPF já cadastrado no sistema.");
        }

        if (repository.existsByEmail(data.email())){
            throw new IllegalArgumentException("Erro: Email já utilizado.");
        }

        Broker newBroker = new Broker();
        newBroker.setName(data.name());
        newBroker.setEmail(data.email());
        newBroker.setCpf(data.cpf());
        newBroker.setActive(true);

        repository.save(newBroker);

        return new BrokerResponseDTO(newBroker);
    }
}
