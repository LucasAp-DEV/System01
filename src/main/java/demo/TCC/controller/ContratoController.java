package demo.TCC.controller;

import demo.TCC.domain.contrato.Contrato;
import demo.TCC.domain.contrato.ContratoDTO;
import demo.TCC.domain.contrato.UpdateContratoDTO;
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

    @GetMapping("/user/{id}")
    public List<ContratoDTO> getContratosByLocatarioOuLocador(@PathVariable(value = "id") Long Id) {
        return service.returnByLocatarioOuLocador(Id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContratoDTO> getByContratosById(@PathVariable(value = "id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findByUserId(id));
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerContrato(@RequestBody @Valid Contrato data) {
        service.saveContrato(data);
        return ResponseEntity.ok().body("Contrado Cadastrado");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateContrato(@PathVariable(value = "id") Long id, @RequestBody UpdateContratoDTO data) {
        return service.updateById(id, data);
    }
}
