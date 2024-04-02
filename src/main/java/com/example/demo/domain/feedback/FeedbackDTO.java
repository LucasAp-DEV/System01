package com.example.demo.domain.feedback;

import com.example.demo.domain.contrato.Contrato;
import com.example.demo.domain.local.Local;

public record FeedbackDTO(Long id,String descricao, int nota, Contrato contratoId) {
}
