package com.example.demo.controller;

import com.example.demo.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("images")
public class ImageController {

    @Autowired
    private ImageService service;

    @PostMapping("/register")
    public ResponseEntity<String> registerImage(@RequestParam("file") MultipartFile file, @RequestParam("localId") Long id) {
        return service.saveImage2(file, id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteImage(@PathVariable(value = "id") Long id) {
        service.dellImage(id);
        return ResponseEntity.status(HttpStatus.OK).body("Imagen deletada");
    }


    @GetMapping("/list")
    public ResponseEntity getAllImages() {
        return ResponseEntity.status(HttpStatus.OK).body(service.returnall());
    }
}