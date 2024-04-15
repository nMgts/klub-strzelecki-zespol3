package pl.klubstrzelecki.serwer_klub_strzelecki.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.klubstrzelecki.serwer_klub_strzelecki.model.News;
import pl.klubstrzelecki.serwer_klub_strzelecki.repository.NewsRepository;
import pl.klubstrzelecki.serwer_klub_strzelecki.service.NewsService;

@RestController
public class NewsController {

    @Autowired
    private NewsRepository newsRepository;
    @Autowired
    private NewsService newsService;

    @GetMapping("/news")
    public ResponseEntity<Object> getAllNews() {
        return ResponseEntity.ok(newsRepository.findAll());
    }

    @PostMapping("/news/save")
    @PreAuthorize("hasAuthority('ADMIN')")
    public News createNews(@RequestBody News news) throws Exception {
        return newsRepository.save(news);
    }

    @RequestMapping("/news/delete/{newsId}")
    @PreAuthorize("hasAuthority('ADMIN')")
//    public String deleteNews(@PathVariable Long newsId) throws Exception {
//        newsService.deleteNews(newsId);
//        return "News deleted successfully";
//    }
    public ResponseEntity<String> deleteNews(@PathVariable("newsId") Long newsId) throws Exception {
        newsRepository.deleteById(newsId);
        return ResponseEntity.ok("News deleted successfully!.");
    }
}