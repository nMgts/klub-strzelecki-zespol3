package pl.klubstrzelecki.serwer_klub_strzelecki.service;

import org.springframework.http.ResponseEntity;
import pl.klubstrzelecki.serwer_klub_strzelecki.dto.NewsDTO;
import pl.klubstrzelecki.serwer_klub_strzelecki.model.News;

public interface NewsService {
    NewsDTO findNewsById(long newsId) throws Exception;
    //void deleteNewsById(long id) throws Exception;
    void deleteNewsById(Long id);
    NewsDTO saveNews(NewsDTO news);
}








