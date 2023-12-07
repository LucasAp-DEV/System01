package com.example.demo.service;

import com.example.demo.model.Center;
import com.example.demo.repository.CenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CenterService {

    @Autowired
    private CenterRepository repository;

    public Center saveCenter(Center center) {
        return repository.save(center);
    }

    public List<Center> findAll() {
        return repository.findAll();
    }

    public Optional<Center> findById(Long id) {
        return repository.findById(id);
    }

    public void dellCenter(Optional<Center> center) {
        repository.delete(center.get());
    }
}
