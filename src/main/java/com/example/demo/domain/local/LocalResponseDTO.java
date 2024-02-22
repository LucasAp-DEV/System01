package com.example.demo.domain.local;

import com.example.demo.domain.user.User;

public record LocalResponseDTO(Long id, String descricao, User userId) {

    public LocalResponseDTO(Local local){
        this(local.getId(), local.getDescricao(), local.getUserId());
    }
}
