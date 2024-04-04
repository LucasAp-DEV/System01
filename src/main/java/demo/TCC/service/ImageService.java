package demo.TCC.service;

import demo.TCC.domain.image.Image;
import demo.TCC.domain.image.ImageDTO;
import demo.TCC.domain.local.Local;
import demo.TCC.repository.ImageRepository;
import demo.TCC.repository.LocalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class ImageService {

    @Autowired
    private ImageRepository repository;

    @Autowired
    private LocalRepository localRepository;

    public List<ImageDTO> returnAll() {
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

    public void saveImage(Image image) {
        repository.save(image);
    }

    public Image findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Image não Encontrada"));
    }

    public Local findByIdLocal(Long id) {
        return localRepository.findById(id).orElseThrow(() -> new RuntimeException("Local não Encontrada"));
    }

    public void dellImage(Long id) {
        var image = findById(id);
        repository.delete(image);
    }

    public ResponseEntity<String> saveImage2(MultipartFile file, Long id) {
        try {
            var imageData = file.getBytes();
            var local = findByIdLocal(id);
            var imageBase64 = Base64.getEncoder().encode(imageData);
            Image image = new Image(imageBase64, local);
            saveImage(image);
            return ResponseEntity.ok().body("Imagen Cadastrada");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Imagen Não Cadastrada");
        }
    }
}
