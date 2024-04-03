package com.example.demo.service;

import com.example.demo.domain.image.Image;
import com.example.demo.domain.image.ImageDTO;
import com.example.demo.domain.local.Local;
import com.example.demo.repository.ImageRepository;
import com.example.demo.repository.LocalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public class ImageService {

    @Autowired
    private ImageRepository repository;

    @Autowired
    private LocalRepository localRepository;


    public List<ImageDTO> returnall(){
        List<Image> imageList = repository.findAll();
        List<ImageDTO> imageDTOS = new ArrayList<>();

        for (Image image : imageList) {
            ImageDTO imageDTO = new ImageDTO(
                    image.getImage(),
                    image.getLocal().getId()
            );
            imageDTOS.add(imageDTO);
        }
        return imageDTOS;
    }

    public Image saveImage(Image image){
        return repository.save(image);
    }

    public Image finById(Long id) {
       return repository.findById(id).orElseThrow(()-> new RuntimeException("Image não Encontrada"));
    }

    public Local finByIdLocal(Long id) {
        return localRepository.findById(id).orElseThrow(()-> new RuntimeException("Local não Encontrada"));
    }

    public void dellImage(Long id){
        var image = finById(id);
        repository.delete(image);
    }

    public ResponseEntity<String> saveImage2(MultipartFile file, Long id){
        try {
            var imageData = file.getBytes();
            var local = finByIdLocal(id);
            Image image = new Image(imageData, local);
            saveImage(image);
            return ResponseEntity.ok().body("Imagen Cadastrada");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Imagen Não Cadastrada");
        }
    }

}
