package demo.TCC.domain.contrato;


import java.time.LocalDate;

public record ContratoDTO(String descricao, LocalDate data, String status, Long local, String name) {
}
