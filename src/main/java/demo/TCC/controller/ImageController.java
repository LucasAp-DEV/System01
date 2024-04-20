package demo.TCC.controller;

import demo.TCC.service.ImageService;
import demo.TCC.domain.image.ImageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("images")
public class ImageController {

    @Autowired
    private ImageService service;

    @PostMapping("/register")
    public ResponseEntity<String> registerImage(@RequestParam("images") String base64Image, @RequestParam("localId") Long id) {
        return service.saveImage2(base64Image, id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteImage(@PathVariable(value = "id") Long id) {
        service.dellImage(id);
        return ResponseEntity.status(HttpStatus.OK).body("Imagen deletada");
    }

    @GetMapping("/list")
    public ResponseEntity<List<ImageDTO>> getAllImages() {
        return ResponseEntity.status(HttpStatus.OK).body(service.returnAll());
    }
}