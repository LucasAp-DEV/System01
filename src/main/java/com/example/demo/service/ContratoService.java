package com.example.demo.service;

import com.example.demo.domain.contrato.Contrato;
import com.example.demo.repository.ContratoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ContratoService {

    @Autowired
    private ContratoRepository repository;

    public List<Contrato> returnall() {
        return repository.findAll();
    }

    public Contrato saveContrato(Contrato contrato) {
        return repository.save(contrato);
    }

    public Contrato returnId(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Contrato não Encontrado"));
    }

    public ResponseEntity<String> updateById(Long id, Contrato data) {
        var contrato = returnId(id);
        validate(data);
        contrato.setDescricao(data.getDescricao());
        contrato.setStatus(data.getStatus());
        contrato.setData(data.getData());

        repository.save(contrato);

        return ResponseEntity.status(HttpStatus.OK).body("Update Realizado");
    }

    //Validando todos os compos que é necessario fazer a alteração
    private void validate(Contrato data) {
        if (Objects.isNull(data.getStatus()))
            throw new RuntimeException("Status é requirido");
        if (Objects.isNull(data.getDescricao()))
            throw new RuntimeException("Status é requirido");
        if (Objects.isNull(data.getData()))
            throw new RuntimeException("Status é requirido");

    }

    public void dellContrato(Long id) {
        var dell = returnId(id);
        repository.delete(dell);
    }
}


