package pl.klubstrzelecki.serwer_klub_strzelecki.service;

import pl.klubstrzelecki.serwer_klub_strzelecki.dto.NewsDTO;

public interface NewsService {
    NewsDTO findNewsById(long newsId) throws Exception;
    void deleteNewsById(Long id);
    NewsDTO saveNews(NewsDTO news);
}
