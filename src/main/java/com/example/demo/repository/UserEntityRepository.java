package com.example.demo.repository;

import com.example.demo.model.user.user.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserEntityRepository extends JpaRepository<Client, String> {
    UserDetails findByLogin(String login);
}
