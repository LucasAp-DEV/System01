package demo.TCC.domain.local;

import lombok.Builder;
import java.util.List;

@Builder
public record LocalDTO(Long id,String endereco, String descricao, Integer price, String userName, List<byte[]> images, String cidade) {
}
