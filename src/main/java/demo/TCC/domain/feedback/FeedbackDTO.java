package demo.TCC.domain.feedback;

import demo.TCC.domain.contrato.Contrato;

public record FeedbackDTO(Long id,String descricao, int nota, Contrato contratoId) {
}
