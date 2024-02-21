package com.example.demo.domain.service;

import com.example.demo.domain.user.User;

public record ServiceResponseDTO(Long id, String descricao, User userId) {

    public ServiceResponseDTO(Service service){
        this(service.getId(), service.getDescricao(), service.getUserId());
    }
}
