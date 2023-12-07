package com.example.demo.controller;

import com.example.demo.model.Sector;
import com.example.demo.repository.SectorRepository;
import com.example.demo.service.SectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/sector")
public class SectorController {
    @Autowired
    private SectorRepository sectorRepository;
    @Autowired
    private SectorService service;

    @PostMapping
    public ResponseEntity<?> saveSector(@RequestBody Sector sector) {
        return ResponseEntity.status(HttpStatus.OK).body(service.saveSector(sector));
    }

    @GetMapping
    public ResponseEntity<List<Sector>> returnAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Sector>> findById(@PathVariable(value = "id")Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> alterSector(@PathVariable(value = "id") Long id, @RequestBody Sector sector) {
        Optional<Sector> busca = service.findById(id);

        if (busca.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body("Setor não encontrado");
        }
        Sector sector1 = busca.get();
        sector1.setNome(sector.getNome());
        sector1.setLider(sector.getLider());

        return ResponseEntity.status(HttpStatus.OK).body(service.saveSector(sector1));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> dellSector(@PathVariable(value = "id") Long id, @RequestBody Sector sector) {
        Optional<Sector> busca = service.findById(id);

        if (busca.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body("Setor não encontrado");
        }
        service.dellSector(busca);

        return ResponseEntity.status(HttpStatus.OK).body("Setor deletado");

    }
}
