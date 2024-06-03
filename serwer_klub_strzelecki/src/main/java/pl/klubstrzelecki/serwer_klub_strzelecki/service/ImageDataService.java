package pl.klubstrzelecki.serwer_klub_strzelecki.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.klubstrzelecki.serwer_klub_strzelecki.model.ImageData;
import pl.klubstrzelecki.serwer_klub_strzelecki.repository.ImageDataRepository;
import pl.klubstrzelecki.serwer_klub_strzelecki.util.ImageUtils;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ImageDataService {

    @Autowired
    private final ImageDataRepository imageDataRepository;

    public ImageDataService(ImageDataRepository imageDataRepository) {
        this.imageDataRepository = imageDataRepository;
    }

    public String uploadImage(MultipartFile file) throws IOException {
        ImageData imageData = imageDataRepository.save(ImageData.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .imageData(ImageUtils.compressImage(file.getBytes())).build());
        if (imageData != null) {
            return "file uploaded successfully : " + file.getOriginalFilename();
        }
        return null;
    }

    public byte[] downloadImage(String fileName) {
        Optional<ImageData> dbImageData = imageDataRepository.findByName(fileName);
        byte[] images = ImageUtils.decompressImage(dbImageData.get().getImageData());
        return images;
    }

    public List<ImageData> getAllImages() {
        return imageDataRepository.findAll().stream()
                .map(image -> ImageData.builder()
                        .name(image.getName())
                        .type(image.getType())
                        .imageData(ImageUtils.decompressImage(image.getImageData()))
                        .build())
                .collect(Collectors.toList());
    }
}
