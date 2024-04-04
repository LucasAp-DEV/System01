package demo.TCC.controller;

import demo.TCC.domain.contrato.Contrato;
import demo.TCC.domain.contrato.ContratoDTO;
import demo.TCC.service.ContratoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("contrato")
public class ContratoController {

    @Autowired
    private ContratoService service;

    @GetMapping("/list")
    public ResponseEntity<List<ContratoDTO>> returnAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.returnall());
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerContrato(@RequestBody @Valid Contrato data) {
        service.saveContrato(data);
        return ResponseEntity.ok().body("Contrado Cadastrado");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateContrato(@PathVariable(value = "id") Long id, @RequestBody Contrato data) {
        return service.updateById(id, data);
    }
}
