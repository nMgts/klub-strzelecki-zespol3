package pl.klubstrzelecki.serwer_klub_strzelecki.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.klubstrzelecki.serwer_klub_strzelecki.model.ImageData;
import pl.klubstrzelecki.serwer_klub_strzelecki.service.ImageDataService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/images")
public class ImageDataController {

    private ImageDataService imageDataService;

    @Autowired
    public ImageDataController(ImageDataService imageDataService) {
        this.imageDataService = imageDataService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile file) throws IOException {
        String uploadImage = imageDataService.uploadImage(file);
        return ResponseEntity.status(HttpStatus.OK)
                .body(uploadImage);
    }

    @GetMapping("/{fileName}")
    public ResponseEntity<?> downloadImage(@PathVariable String fileName){
        byte[] imageData=imageDataService.downloadImage(fileName);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(imageData);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ImageData>> getAllImages() {
        List<ImageData> images = imageDataService.getAllImages();
        return ResponseEntity.status(HttpStatus.OK)
                .body(images);
    }
}
