package com.example.demo.controller;

import com.example.demo.domain.image.Image;
import com.example.demo.domain.image.ImageRegisterDTO;
import com.example.demo.domain.image.UpdateImageDTO;
import com.example.demo.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("images")
public class ImageController {

    @Autowired
    private ImageRepository imageRepository;

    @PostMapping("/register")
    public ResponseEntity registerImage(@RequestBody ImageRegisterDTO imageDTO) {
        try {
            Image image = new Image(imageDTO.name(), imageDTO.image());
            this.imageRepository.save(image);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Falha mo registro da imagen");
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateImage(@PathVariable Long id, @RequestBody UpdateImageDTO updateImageDTO) {
        Optional<Image> optionalImage = imageRepository.findById(id);
        if (optionalImage.isPresent()) {
            Image image = optionalImage.get();
            image.setName(updateImageDTO.name());
            image.setImage(updateImageDTO.image());
            this.imageRepository.save(image);
            return ResponseEntity.status(HttpStatus.OK).body("Image updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Image not found");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteImage(@PathVariable Long id) {
        Optional<Image> optionalImage = imageRepository.findById(id);
        if (optionalImage.isPresent()) {
            this.imageRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Imagen Deletada");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Imagen Não Deletada");
        }
    }

    @GetMapping("/list")
    public ResponseEntity<List<Image>> getAllImages() {
        List<Image> images = imageRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(images);
    }
}

