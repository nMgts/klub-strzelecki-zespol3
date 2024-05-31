package pl.klubstrzelecki.serwer_klub_strzelecki.controller;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.klubstrzelecki.serwer_klub_strzelecki.dto.NewsDTO;
import pl.klubstrzelecki.serwer_klub_strzelecki.model.News;
import pl.klubstrzelecki.serwer_klub_strzelecki.repository.NewsRepository;
import pl.klubstrzelecki.serwer_klub_strzelecki.service.NewsService;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.Optional;

@RestController
@RequestMapping("/api/news")
public class NewsController {

    private final NewsService newsService;

    @Autowired
    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping("/all")
    public ResponseEntity<Object> getAllNews() {
        return ResponseEntity.ok().body(newsService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getNewsById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok().body(newsService.findNewsById(id));
    }

    @PostMapping("/add")
    //@PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Object> createNews(@RequestBody NewsDTO news) {
        newsService.saveNews(news);
        return ResponseEntity.ok().body("{\"message\": \"News saved successfully!\"}");
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Object> updateNews(@PathVariable Long id, @RequestBody NewsDTO newsDTO) throws Exception {
        newsService.updateNews(id, newsDTO);
        return ResponseEntity.ok().body("{\"message\": \"News updated successfully!.\"}");
    }

    @DeleteMapping("/delete/{id}")
    //@PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> deleteNews(@PathVariable("id") Long id) throws Exception {
            newsService.deleteNewsById(id);
        return ResponseEntity.ok().body("{\"message\": \"News deleted successfully!.\"}");
    }
}
