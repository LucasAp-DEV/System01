package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserEntityRepository extends JpaRepository<User, String> {
    UserDetails findByLogin(String login);
}
