package demo.TCC.domain.contrato;


import java.time.LocalDate;

public record ContratoDTO(LocalDate data, String status, Long local, String name) {
}
