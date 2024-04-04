package demo.TCC.domain.contrato;

import demo.TCC.domain.local.Local;
import demo.TCC.domain.user.User;

import java.time.LocalDate;

public record RegisterContratoDTO(String descricao, LocalDate data, String status, User userId, Local localId) {

}
