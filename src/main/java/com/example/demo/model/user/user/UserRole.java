package com.example.demo.model.user.user;

public enum UserRole {

    CLIENT("client"),
    USER("user");

    private String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
