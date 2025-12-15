package com.agency.commission.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

public record BrokerRequestDTO(
    @NotBlank
    String name,

    @NotBlank
    @CPF
    String cpf,

    @NotBlank
    @Email
    String email
){}