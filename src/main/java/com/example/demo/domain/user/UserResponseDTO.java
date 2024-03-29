package com.example.demo.domain.user;

import com.example.demo.domain.cidade.Cidade;

public record UserResponseDTO(Long id, String login, UserRole role, String nome, String email, String telephone, Cidade cidade) {
    public UserResponseDTO(User user){
        this(user.getId(), user.getLogin(), user.getRole(), user.getNome(), user.getEmail(), user.getTelephone(), user.getCidadeId());
    }
}