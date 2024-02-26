package com.example.demo.controller;

import com.example.demo.domain.local.RegisterLocalDTO;
import com.example.demo.domain.local.Local;
import com.example.demo.domain.local.UpdateLocalDTO;
import com.example.demo.repository.LocalRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("local")
public class LocalController {

    @Autowired
    private LocalRepository repository;

    @PostMapping("/register")
    public ResponseEntity registerLocal(@RequestBody @Valid RegisterLocalDTO data) {
        Local newLocal = new Local(data.descricao(),data.price(), data.userId(), data.endereco());

        this.repository.save(newLocal);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/update")
    public ResponseEntity updateLocal(@RequestBody UpdateLocalDTO data) {
        Local updatLocal = repository.getReferenceById(data.id());

        if(data.descricao() != null) {updatLocal.setDescricao(data.descricao());}
        if(data.price() != null) {updatLocal.setPrice(data.price());}
        if(data.endereco() != null) {updatLocal.setEndereco(data.endereco());}

        this.repository.save(updatLocal);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/delete")
    public  ResponseEntity deleteLocal(@RequestBody UpdateLocalDTO data) {
        Local dellLocal = repository.getReferenceById(data.id());
        this.repository.delete(dellLocal);

        return  ResponseEntity.ok().build();
    }

}
