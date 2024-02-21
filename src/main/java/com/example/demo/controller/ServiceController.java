package com.example.demo.controller;

import com.example.demo.domain.service.RegisterServiceDTO;
import com.example.demo.domain.service.Service;
import com.example.demo.repository.ServiceRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("service")
public class ServiceController {

    @Autowired
    private ServiceRepository repository;

    @PostMapping("/register")
    public ResponseEntity registerService(@RequestBody @Valid RegisterServiceDTO data) {
        Service newService = new Service(data.descricao(),data.price(), data.userId());

        this.repository.save(newService);

        return ResponseEntity.ok().build();
    }
}
