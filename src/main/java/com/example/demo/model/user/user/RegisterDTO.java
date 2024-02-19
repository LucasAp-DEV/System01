package com.example.demo.model.user.user;

public record RegisterDTO(String login, String password, UserRole role) {
}