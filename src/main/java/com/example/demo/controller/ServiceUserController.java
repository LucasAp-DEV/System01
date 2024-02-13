package com.example.demo.controller;

import com.example.demo.model.user.servicoUser.ServiceResponserDTO;
import com.example.demo.model.user.servicoUser.ServicoUserEntity;
import com.example.demo.repository.ServicosUserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("serviceuser")
public class ServiceUserController {

    @Autowired
    ServicosUserRepository repository;

    @PostMapping("/service")
    public ResponseEntity postServiceUser(@RequestBody @Valid ServiceResponserDTO body) {
        ServicoUserEntity newServiceUser = new ServicoUserEntity(body);

        this.repository.save(newServiceUser);
        return ResponseEntity.ok().build();
    }
}
