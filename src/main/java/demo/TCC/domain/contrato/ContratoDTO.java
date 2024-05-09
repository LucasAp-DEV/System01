package demo.TCC.domain.contrato;


import lombok.Builder;

import java.time.LocalDate;

@Builder
public record ContratoDTO(
        Long id, LocalDate data, String status, Long local, String locador, String locatario,
        String telephone, Integer price, String endereco, String cidade, Long locadorId, Long locatarioId
) {
}
