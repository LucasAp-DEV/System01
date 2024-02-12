package com.example.demo.model.user.user;

import com.example.demo.model.user.user.UserEntity;
import com.example.demo.model.user.user.UserRole;

public record UserResponseDTO(Long id, String login, UserRole role) {
    public UserResponseDTO(UserEntity user){
        this(user.getId(), user.getLogin(), user.getRole());
    }
}
