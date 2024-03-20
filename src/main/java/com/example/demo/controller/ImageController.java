package com.example.demo.controller;

import com.example.demo.domain.image.Image;
import com.example.demo.domain.image.UpdateImageDTO;
import com.example.demo.domain.local.Local;
import com.example.demo.repository.ImageRepository;
import com.example.demo.repository.LocalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("images")
public class ImageController {

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private LocalRepository localRepository;

    @PostMapping("/register")
    public ResponseEntity registerImage(@RequestParam("file") MultipartFile file,
                                        @RequestParam("localId") Long localId) {
        try {
            byte[] imageData = file.getBytes();
            Local local = localRepository.findById(localId).orElse(null);
            if (local == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Local Nao Encontrado");
            }
            Image image = new Image(imageData, local);
            this.imageRepository.save(image);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to register image");
        }
    }



//    @PutMapping("/update")
//    public ResponseEntity updateImage(@RequestBody UpdateImageDTO data) {
//        Optional<Image> optionalImage = imageRepository.findById(data.id());
//        if (optionalImage.isPresent()) {
//            Image image = optionalImage.get();
//            image.setImage(data.image());
//            this.imageRepository.save(image);
//            return ResponseEntity.status(HttpStatus.OK).body("Image updated successfully");
//        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Image not found");
//        }
//    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteImage(@RequestBody UpdateImageDTO data) {
        Optional<Image> optionalImage = imageRepository.findById(data.id());
        if (optionalImage.isPresent()) {
            this.imageRepository.deleteById(data.id());
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