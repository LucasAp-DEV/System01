package com.example.demo.controller;

import com.example.demo.domain.cidade.Cidade;
import com.example.demo.domain.cidade.RegisterCidadeDTO;
import com.example.demo.domain.cidade.UpdatecidadeDTO;
import com.example.demo.repository.CidadeRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("cidade")
public class CidadeController {
    @Autowired
    private CidadeRepository repository;

    @PostMapping("/register")
    public ResponseEntity<String> registerCidade(@RequestBody @Valid RegisterCidadeDTO data) {
        if (this.repository.findByName(data.name()) != null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cidade ja cadastrada"); //Implimentar IF.

        Cidade newCidade = new Cidade(data.name());
        this.repository.save(newCidade);
        return ResponseEntity.status(HttpStatus.OK).body("Cidade cadastrada com sucesso"); //Body ou Build ???
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateCidade(@RequestBody @Valid UpdatecidadeDTO data) {
        var upadteCidade = repository.findById(data.id()).orElseThrow(()-> new RuntimeException("Cidade não encontrada")); //Verificar nos outros consoles.

//        if(optionalCidade.isEmpty())
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cidade não encontrada");
//        Cidade upadteCidade = repository.getReferenceById(data.id());

        if(data.name() != null) {upadteCidade.setName(data.name());}
        this.repository.save(upadteCidade);

        return ResponseEntity.status(HttpStatus.OK).body("Cidade Atualizada");
    }

}
