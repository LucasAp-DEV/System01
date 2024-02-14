package com.example.demo.service;

import com.example.demo.model.user.servicoUser.ServiceUser;
import com.example.demo.repository.ServicosUserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ServiceUserService {

    @Autowired
    private ServicosUserRepository repository;

    public ServiceUser saveService (ServiceUser serviceUser) {
        return repository.save(serviceUser);
    }
}
