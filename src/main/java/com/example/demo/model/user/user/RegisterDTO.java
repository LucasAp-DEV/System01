package com.example.demo.model.user.user;

import com.example.demo.model.user.user.UserRole;

public record RegisterDTO
        (String nome, String sexo, String contato, String email, String login, String password, UserRole role){
}
