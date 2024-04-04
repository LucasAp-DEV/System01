package demo.TCC.domain.local;

import lombok.Builder;

@Builder
public record LocalDTO(Long id,String endereco, String descricao, Integer price, Long userId) {

}
