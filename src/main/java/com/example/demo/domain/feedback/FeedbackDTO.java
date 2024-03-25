package com.example.demo.domain.feedback;

import com.example.demo.domain.local.Local;

public record FeedbackDTO(Long id,String descricao, int nota, Local localId) {
}
