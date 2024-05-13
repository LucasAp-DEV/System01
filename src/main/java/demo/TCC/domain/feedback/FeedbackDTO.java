package demo.TCC.domain.feedback;

import lombok.Builder;

@Builder
public record FeedbackDTO(String descricao, int nota, String nome) {
}
