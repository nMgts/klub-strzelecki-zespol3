package pl.klubstrzelecki.serwer_klub_strzelecki.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.klubstrzelecki.serwer_klub_strzelecki.model.News;
import pl.klubstrzelecki.serwer_klub_strzelecki.repository.NewsRepository;

@RestController
public class NewsController {

    @Autowired
    private NewsRepository newsRepository;

    @GetMapping("/news")
    public ResponseEntity<Object> getAllNews() {
        return ResponseEntity.ok(newsRepository.findAll());
    }

    @PostMapping("/news/save")
    @PreAuthorize("hasAuthority('ADMIN')")
    public News createNews(@RequestBody News news) throws Exception {
        return newsRepository.save(news);
    }

    @DeleteMapping("/news/delete/{newsId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteNews(@PathVariable Long newsId) {
        newsRepository.deleteById(newsId);
        return "News deleted successfully";
    }
}