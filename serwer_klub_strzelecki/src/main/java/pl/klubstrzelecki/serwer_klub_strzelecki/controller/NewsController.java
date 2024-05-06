package pl.klubstrzelecki.serwer_klub_strzelecki.controller;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.klubstrzelecki.serwer_klub_strzelecki.dto.NewsDTO;
import pl.klubstrzelecki.serwer_klub_strzelecki.model.News;
import pl.klubstrzelecki.serwer_klub_strzelecki.repository.NewsRepository;
import pl.klubstrzelecki.serwer_klub_strzelecki.service.NewsService;

@RestController
@RequestMapping("/api/news")
public class NewsController {

    @Autowired
    private NewsRepository newsRepository;
    @Autowired
    private NewsService newsService;

    @GetMapping("/all")
    public ResponseEntity<Object> getAllNews() {
        return ResponseEntity.ok(newsRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getNewsById(@PathVariable Long id) {
        return ResponseEntity.ok(newsRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("News not exists with id: " + id)));
    }

    @PostMapping("/add")
    //@PreAuthorize("hasAuthority('ADMIN')")
    public NewsDTO createNews(@RequestBody NewsDTO news) throws Exception {
        return newsService.saveNews(news);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Object> updateNews(@PathVariable Long id, @RequestBody News newsDetails) {
        News news = newsRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("News not exists with id: " + id));
        news.setTitle(newsDetails.getTitle());
        news.setContent(newsDetails.getContent());

        News updatedNews = newsRepository.save(news);
        return ResponseEntity.ok(updatedNews);
    }

    @RequestMapping("/delete/{newsId}")
    //@PreAuthorize("hasAuthority('ADMIN')")
//    public String deleteNews(@PathVariable Long newsId) throws Exception {
//        newsService.deleteNews(newsId);
//        return "News deleted successfully";
//    }
    public ResponseEntity<String> deleteNews(@PathVariable("newsId") Long newsId) throws Exception {
        newsService.deleteNewsById(newsId);
        return ResponseEntity.ok("News deleted successfully!.");
    }
}
