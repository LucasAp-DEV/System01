package com.example.demo.domain.user;

public record UpdateUserDTO(Long id, String login, String password, String nome, String email, String telephone,
                            String sexo) {
}
