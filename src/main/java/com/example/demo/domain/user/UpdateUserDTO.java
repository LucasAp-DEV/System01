package com.example.demo.domain.user;

import com.example.demo.domain.cidade.Cidade;

public record UpdateUserDTO(Long id, String login, String password, String nome, String email, String telephone,
                            String sexo, Cidade cidadeId) {
}
