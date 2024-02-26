package com.example.demo.domain.local;


import com.example.demo.domain.user.User;

public record RegisterLocalDTO(String descricao, Integer price, User userId, String endereco) {
}
