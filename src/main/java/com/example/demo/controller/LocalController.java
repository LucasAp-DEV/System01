package com.example.demo.controller;

import com.example.demo.domain.local.RegisterLocalDTO;
import com.example.demo.domain.local.Local;
import com.example.demo.repository.LocalRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("local")
public class LocalController {

    @Autowired
    private LocalRepository repository;

    @PostMapping("/register")
    public ResponseEntity registerLocal(@RequestBody @Valid RegisterLocalDTO data) {
        Local newLocal = new Local(data.descricao(),data.price(), data.userId(), data.endereco());

        this.repository.save(newLocal);

        return ResponseEntity.ok().build();
    }

}
