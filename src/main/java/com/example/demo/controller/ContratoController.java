package com.example.demo.controller;

import com.example.demo.domain.contrato.Contrato;
import com.example.demo.domain.contrato.RegisterContratoDTO;
import com.example.demo.domain.contrato.UpdateContratoDTO;
import com.example.demo.repository.ContratoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("contrato")
public class ContratoController {

    @Autowired
    private ContratoRepository repository;

    @PostMapping("/register")
    public ResponseEntity registerContrato(@RequestBody @Valid  RegisterContratoDTO data) {
        Contrato newContrato = new Contrato(data.descricao(),data.data(), data.userId(), data.localId(), data.status());

        this.repository.save(newContrato);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/update")
    public ResponseEntity updateContrato(@RequestBody UpdateContratoDTO data) {
        Contrato updateContrato = repository.getReferenceById(data.id());

        if(data.descricao() != null) {updateContrato.setDescricao(data.descricao());}
        if(data.status() != null) {updateContrato.setStatus(data.status());}
        if(data.data() != null) {updateContrato.setData(data.data());}

        this.repository.save(updateContrato);

        return ResponseEntity.ok().build();
    }
}
