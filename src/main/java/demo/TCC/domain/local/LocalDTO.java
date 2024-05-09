package demo.TCC.domain.local;

import demo.TCC.domain.contrato.Contrato;
import demo.TCC.domain.contrato.ContratoDTO;
import lombok.Builder;
import java.util.List;

@Builder
public record LocalDTO(Long id, String endereco, String descricao, Integer price, String locatarioName,
                       List<byte[]> images, String cidade, Long locatarioId, String locatarioTell, List<ContratoDTO> contratos) {
}
