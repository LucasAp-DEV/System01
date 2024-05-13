package demo.TCC.controller;

import demo.TCC.domain.local.Local;
import demo.TCC.domain.local.LocalDTO;
import demo.TCC.service.LocalService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("local")
public class LocalController {

    @Autowired
    private LocalService service;

    @PostMapping("/register")
    public ResponseEntity<Long> registerLocal(@RequestBody @Valid Local local) {
        Long localId = service.saveLocal(local);
        return ResponseEntity.status(HttpStatus.OK).body(localId);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateLocal(@PathVariable(value = "id")Long id, @RequestBody Local data) {
        service.updateLocal(id, data);
        return ResponseEntity.status(HttpStatus.OK).body("Atualizado com Sucesso");
    }

    @DeleteMapping("/delete/{id}")
    public  ResponseEntity<String> deleteLocal(@PathVariable(value = "id")Long id) {
        service.dellLocal(id);
        return ResponseEntity.status(HttpStatus.OK).body("Local Deletado");
    }

    @GetMapping("/list")
    public ResponseEntity<List<LocalDTO>> getAllLocal() {
        return ResponseEntity.status(HttpStatus.OK).body(service.returnAll());
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<List<LocalDTO>> getAllLocalUserId(@PathVariable(value = "id")Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.returUserId(id));
    }

    @GetMapping("/{id}") //Inserir Imagen e verificar
    public ResponseEntity<?> getByIdLocal(@PathVariable(value = "id")Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findByLocalId(id));
    }
}
