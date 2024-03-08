package com.example.demo.domain.user;

import com.example.demo.domain.cidade.Cidade;

public record RegisterUserDTO(String login, String password, UserRole role, String nome, String email, String telephone, Cidade cidadeId) {
}

