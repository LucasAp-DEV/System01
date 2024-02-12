package com.example.demo.model.user;

public record UserResponseDTO(Long id, String login, UserRole role) {
    public UserResponseDTO(UserEntity user){
        this(user.getId(), user.getLogin(), user.getRole());
    }
}
