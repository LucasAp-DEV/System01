package com.example.demo.domain.contrato;

import java.time.LocalDate;

public record ContratoResponseDTO(String descricao, LocalDate data, String status) {

    public ContratoResponseDTO(Contrato contrato) {
        this(contrato.getDescricao(), contrato.getData(), contrato.getStatus());
    }
}