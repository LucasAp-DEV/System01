package com.example.demo.controller;

import com.example.demo.domain.cidade.Cidade;
import com.example.demo.domain.cidade.RegisterCidadeDTO;
import com.example.demo.domain.cidade.UpdatecidadeDTO;
import com.example.demo.domain.user.UpdateUserDTO;
import com.example.demo.domain.user.User;
import com.example.demo.repository.CidadeRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("cidade")
public class CidadeController {
    @Autowired
    private CidadeRepository repository;

    @PostMapping("/register")
    public ResponseEntity registerCidade(@RequestBody @Valid RegisterCidadeDTO data) {
        Cidade newCidade = new Cidade(data.name());

        this.repository.save(newCidade);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/update")
    public ResponseEntity updateContrato(@RequestBody UpdatecidadeDTO data) {
        Cidade upadteCidade = repository.getReferenceById(data.id());

        if(data.name() != null) {upadteCidade.setName(data.name());}

        this.repository.save(upadteCidade);

        return ResponseEntity.ok().build();
    }

}
