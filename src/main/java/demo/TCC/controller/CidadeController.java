package demo.TCC.controller;

import demo.TCC.domain.cidade.Cidade;
import demo.TCC.service.CidadeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("cidade")
public class CidadeController {

    private final CidadeService service;

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

}
