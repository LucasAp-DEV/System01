package com.example.demo.controller;

import com.example.demo.domain.cidade.Cidade;
import com.example.demo.domain.cidade.RegisterCidadeDTO;
import com.example.demo.domain.cidade.UpdatecidadeDTO;
import com.example.demo.repository.CidadeRepository;
import com.example.demo.service.CidadeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("cidade")
public class CidadeController {

    @Autowired
    private CidadeService service;

    @PostMapping("/register")
    public ResponseEntity<String> registerCidade(@RequestBody @Valid Cidade cidade) {
      var CidadeExistente = service.returnName(cidade.getName());
      if(CidadeExistente != null){
          return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cidade ja Cadastrada");
      }
      service.saveCidade(cidade);
      return ResponseEntity.status(HttpStatus.OK).body("Cidade Cadastrada");
    }

    @GetMapping("/list")
    public ResponseEntity<List<Cidade>> returnAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.returnall());
    }


//    @PutMapping("/update")
//    public ResponseEntity<String> updateCidade(@RequestBody @Valid UpdatecidadeDTO data) {
//        var upadteCidade = repository.findById(data.id()).orElseThrow(()-> new RuntimeException("Cidade não encontrada")); //Verificar nos outros consoles.
//
////        if(optionalCidade.isEmpty())
////        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cidade não encontrada");
////        Cidade upadteCidade = repository.getReferenceById(data.id());
//
//        if(data.name() != null) {upadteCidade.setName(data.name());}
//        this.repository.save(upadteCidade);
//
//        return ResponseEntity.status(HttpStatus.OK).body("Cidade Atualizada");
//    }

}
