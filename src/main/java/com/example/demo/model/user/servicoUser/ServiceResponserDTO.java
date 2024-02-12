package com.example.demo.model.user.servicoUser;

public record ServiceResponserDTO(Long id, String name, Integer price) {
    public ServiceResponserDTO (ServicoUserEntity servicoUser){
        this(servicoUser.getId(), servicoUser.getName(), servicoUser.getPrice());
    }

}
