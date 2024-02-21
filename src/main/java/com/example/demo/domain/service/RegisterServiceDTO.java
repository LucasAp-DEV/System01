package com.example.demo.domain.service;


import com.example.demo.domain.user.User;

public record RegisterServiceDTO(String descricao, float price, User userId) {
}
