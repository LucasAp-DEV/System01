package com.example.demo.domain.contrato;

import com.example.demo.domain.local.Local;
import com.example.demo.domain.user.User;

import java.time.LocalDate;
import java.util.Date;

public record RegisterContratoDTO(String descricao, LocalDate data, String status, User userId, Local localId) {

}
