package demo.TCC.domain.local;

import demo.TCC.domain.feedback.FeedbackDTO;
import lombok.Builder;
import java.util.List;

@Builder
public record LocalDTO(Long id, String endereco, String descricao, Integer price, String locatarioName,
                       List<byte[]> images, String cidade,Long cidadeId, Long locatarioId, String locatarioTell,
                       List<FeedbackDTO> feedback, String status, Boolean disponibilidade) {
}
