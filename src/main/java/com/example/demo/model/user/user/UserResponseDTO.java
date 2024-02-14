package com.example.demo.model.user.user;

public record UserResponseDTO(Long id, String login, UserRole role) {
    public UserResponseDTO(Users user){
        this(user.getId(), user.getLogin(), user.getRole());
    }
}
