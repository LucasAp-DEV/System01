package com.example.demo.service;

import com.example.demo.model.user.servicoUser.ServicoUserEntity;
import com.example.demo.repository.ServicosUserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ServicoUserService {

    @Autowired
    private ServicosUserRepository repository;

    public ServicoUserEntity saveService(ServicoUserEntity servicoUser) {
        return repository.save(servicoUser);
    }
}
