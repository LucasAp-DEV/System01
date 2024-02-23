package com.example.demo.domain.user;

public record RegisterUserDTO(String login, String password, UserRole role, String nome, String email, String telephone, String sexo) {
}

