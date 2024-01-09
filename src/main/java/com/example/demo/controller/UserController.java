package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository repository;
    @Autowired
    private UserService service;

    @PostMapping
    public ResponseEntity<?> saveUser(@RequestBody User user) {
        return ResponseEntity.status(HttpStatus.OK).body(service.saveUser(user));
    }

    @GetMapping
    public ResponseEntity<List<User>> returnAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<User>> findById(@PathVariable(value = "id")Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> alterSector(@PathVariable(value = "id") Long id, @RequestBody User user) {
        Optional<User> busca = service.findById(id);

        if (busca.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body("Usuario não encontrado");
        }
        User user1 = busca.get();
        user1.setName(user1.getName());

        return ResponseEntity.status(HttpStatus.OK).body(service.saveUser(user1));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> dellSector(@PathVariable(value = "id") Long id, @RequestBody User user) {
        Optional<User> busca = service.findById(id);

        if (busca.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body("Usuario não encontrado");
        }
        service.dellUser(busca);

        return ResponseEntity.status(HttpStatus.OK).body("Usuario deletado");

    }
}
