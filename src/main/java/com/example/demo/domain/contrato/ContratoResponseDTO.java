package com.example.demo.domain.contrato;

import java.util.Date;

public record ContratoResponseDTO(String descricao, Date data, String status) {

    public ContratoResponseDTO(Contrato contrato) {
        this(contrato.getDescricao(), contrato.getData(), contrato.getStatus());
    }
}