package com.example.demo.service;

import com.example.demo.domain.image.Image;
import com.example.demo.domain.local.Local;
import com.example.demo.domain.local.LocalResponseDTO;
import com.example.demo.repository.ImageRepository;
import com.example.demo.repository.LocalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {

    @Autowired
    private ImageRepository repository;

    @Autowired
    private LocalRepository localRepository;


    public List<Image> returnall(){
        return repository.findAll();
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
}
