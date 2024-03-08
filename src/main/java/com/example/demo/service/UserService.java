package com.example.demo.service;

import com.example.demo.domain.user.User;
import com.example.demo.repository.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserEntityRepository repository;

    public List<User> findAll() {
        return repository.findAll();
    }
}
