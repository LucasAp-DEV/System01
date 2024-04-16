package demo.TCC.domain.contrato;


import java.time.LocalDate;

public record ContratoDTO(Long id,LocalDate data, String status, Long local, String locador, String locatario) {
}
