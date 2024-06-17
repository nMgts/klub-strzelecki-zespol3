package pl.klubstrzelecki.serwer_klub_strzelecki.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.klubstrzelecki.serwer_klub_strzelecki.model.ImageData;
import pl.klubstrzelecki.serwer_klub_strzelecki.repository.ImageDataRepository;

import java.io.IOException;
import java.util.List;

@Service
public class ImageDataService {
    private final ImageDataRepository imageDataRepository;

    @Autowired
    public ImageDataService(ImageDataRepository imageDataRepository) {
        this.imageDataRepository = imageDataRepository;
    }

    public ImageData getImageDetails(long id) {
        return imageDataRepository.findById(id).orElseThrow(() -> new RuntimeException("Image not found"));
    }

    public List<ImageData> getAllImages() {
        return imageDataRepository.findAll();
    }

    public ImageData addImage(String name, MultipartFile file) throws IOException {
        ImageData imageData = new ImageData();
        imageData.setName(name);
        imageData.setImageData(file.getBytes());
        return imageDataRepository.save(imageData);
    }
}

