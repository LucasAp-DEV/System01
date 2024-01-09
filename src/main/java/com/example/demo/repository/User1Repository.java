package com.example.demo.repository;

import com.example.demo.model.user.User1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface User1Repository extends JpaRepository<User1, String> {

    UserDetails findByLogin(String login);
}
