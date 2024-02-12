package com.example.demo.model.user;

public record RegisterDTO
        (String nome, String sexo, String contato, String email, String login, String password, UserRole role){
}
