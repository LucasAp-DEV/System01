package com.example.demo.model.user.servicoUser;

import com.example.demo.model.user.user.Client;

public record ServiceResponserDTO(Integer id, String name, Integer price) {
    public ServiceResponserDTO (ServicoUserEntity servicoUser){
        this(servicoUser.getId(), servicoUser.getName(), servicoUser.getPrice());
    }
}
