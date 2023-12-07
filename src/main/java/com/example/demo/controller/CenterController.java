package com.example.demo.controller;

import com.example.demo.model.Center;
import com.example.demo.repository.CenterRepository;
import com.example.demo.service.CenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/center")
public class CenterController {

    @Autowired
    private CenterService service;
    @Autowired
    private CenterRepository repository;

    @PostMapping
    public ResponseEntity<?> saveCenter(@RequestBody Center center) {
        return ResponseEntity.status(HttpStatus.OK).body(service.saveCenter(center));
    }

    @GetMapping
    public ResponseEntity<List<Center>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Center>> findById(@PathVariable(value = "id")Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> alterCenter(@PathVariable(value = "id")Long id, @RequestBody Center center) {
        Optional<Center> busca = service.findById(id);

        if (busca.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body("Centro não encontrado");
        }
        Center center1 = busca.get();
        center1.setName(center1.getName());

        return ResponseEntity.status(HttpStatus.OK).body(service.saveCenter(center1));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> dellCenter(@PathVariable(value = "id")Long id, @RequestBody Center center) {
        Optional<Center> busca = service.findById(id);

        if (busca.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body("Centro nao encontrado");
        }
        service.dellCenter(busca);
        return ResponseEntity.status(HttpStatus.OK).body("Centro deletado");
    }
}
