package pl.klubstrzelecki.serwer_klub_strzelecki.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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
    private final ImageDataService imageDataService;

    @Autowired
    public ImageDataController(ImageDataService imageDataService) {
        this.imageDataService = imageDataService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ImageData> getImageDetails(@PathVariable long id) {
        return ResponseEntity.ok(imageDataService.getImageDetails(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ImageData>> getAllImages() {
        return ResponseEntity.ok(imageDataService.getAllImages());
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ImageData> addImage(
            @RequestParam("name") String name,
            @RequestParam("type") String type,
            @RequestParam("image") MultipartFile image
    ) throws IOException {
        return ResponseEntity.ok(imageDataService.addImage(name, type, image));
    }

    @GetMapping("/image/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable long id) {
        ImageData imageData = imageDataService.getImageDetails(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + imageData.getName() + "\"")
                .contentType(MediaType.parseMediaType(imageData.getType()))
                .body(imageData.getImageData());
    }
}

