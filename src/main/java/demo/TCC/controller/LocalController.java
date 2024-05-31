package demo.TCC.controller;

import demo.TCC.domain.local.Local;
import demo.TCC.domain.local.LocalDTO;
import demo.TCC.domain.local.LocalFilterDTO;
import demo.TCC.service.LocalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("local")
public class LocalController {

    private final LocalService service;

    @PostMapping("/register")
    public ResponseEntity<Long> registerLocal(@RequestBody @Valid Local local) {
        Long localId = service.saveLocal(local);
        return ResponseEntity.status(HttpStatus.OK).body(localId);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateLocal(@PathVariable(value = "id")Long id, @RequestBody Local data) {
        return service.updateLocal(id, data);
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

//    @GetMapping("/filter")
//    public ResponseEntity<List<LocalDTO>> getAllLocalFilter(
//            @RequestParam(value = "cityId", required = false, defaultValue = "") Long cityId,
//            @RequestParam(value = "data", required = false, defaultValue = "") LocalDate data) {
//        return ResponseEntity.status(HttpStatus.OK).body(service.returnLocalFilter(cityId, data));
//    }


    @GetMapping("/{id}") //Inserir Imagen e verificar
    public ResponseEntity<?> getByIdLocal(@PathVariable(value = "id")Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findByLocalId(id));
    }
}
