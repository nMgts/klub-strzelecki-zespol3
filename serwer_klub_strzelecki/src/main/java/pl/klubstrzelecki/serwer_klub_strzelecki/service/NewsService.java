package pl.klubstrzelecki.serwer_klub_strzelecki.service;

import pl.klubstrzelecki.serwer_klub_strzelecki.dto.NewsDTO;
import pl.klubstrzelecki.serwer_klub_strzelecki.model.News;

public interface NewsService {
    NewsDTO findNewsById(long newsId) throws Exception;
    void deleteNewsById(long id) throws Exception;
    NewsDTO saveNews(NewsDTO news);
}








