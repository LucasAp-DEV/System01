package com.example.demo.domain.contrato;

import com.example.demo.domain.local.Local;
import com.example.demo.domain.user.User;

import java.util.Date;

public record RegisterContratoDTO(String descricao, Date data, String status, User userId, Local localId) {

}
