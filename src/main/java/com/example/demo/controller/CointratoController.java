package com.example.demo.controller;

import com.example.demo.domain.contrato.Contrato;
import com.example.demo.domain.contrato.RegisterContratoDTO;
import com.example.demo.repository.ContratoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("contrato")
public class CointratoController {

    @Autowired
    private ContratoRepository repository;

    @PostMapping("/register")
    public ResponseEntity registerContrato(@RequestBody @Valid  RegisterContratoDTO data) {
        Contrato newContrato = new Contrato(data.descricao(),data.data(), data.userId(), data.localId(), data.status());

        this.repository.save(newContrato);

        return ResponseEntity.ok().build();
    }
}
