package com.example.demo.domain.contrato;

import java.time.LocalDate;

public record UpdateContratoDTO(Long id, String descricao, LocalDate data, String status) {
}
