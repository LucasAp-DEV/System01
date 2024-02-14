package com.example.demo.controller;

import com.example.demo.model.user.servicoUser.ServiceUser;
import com.example.demo.repository.ServicosUserRepository;
import com.example.demo.service.ServiceUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @Autowired
    ServiceUserService service;

    @PostMapping("/service")
    public ResponseEntity postServiceUser(@RequestBody ServiceUser servicoUser){
        return  ResponseEntity.status(HttpStatus.OK).body(service.saveService(servicoUser));
    }
}
