package com.agency.commission.repository;

import com.agency.commission.domain.Broker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository

public interface BrokerRepository extends JpaRepository<Broker, Long> {

    Optional<Broker> findByCpf(String cpf);

    boolean existsByCpf (String cpf);
    boolean existsByEmail (String email);
}
