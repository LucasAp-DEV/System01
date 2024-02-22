package com.example.demo.domain.local;


import com.example.demo.domain.user.User;

public record RegisterLocalDTO(String descricao, float price, User userId, String endereco) {
}
