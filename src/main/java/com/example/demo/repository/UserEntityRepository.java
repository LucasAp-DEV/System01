package com.example.demo.repository;

import com.example.demo.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public interface UserEntityRepository extends JpaRepository<User, Long> {
    UserDetails findByLogin(String login);
    Optional<User> findById(Long id);
}
