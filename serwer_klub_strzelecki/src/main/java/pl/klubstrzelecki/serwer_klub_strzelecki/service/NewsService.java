package pl.klubstrzelecki.serwer_klub_strzelecki.service;

import pl.klubstrzelecki.serwer_klub_strzelecki.dto.NewsDTO;
import pl.klubstrzelecki.serwer_klub_strzelecki.model.News;

import java.util.List;

public interface NewsService {
    List<NewsDTO> findAll();
    NewsDTO findNewsById(long id) throws Exception;
    void deleteNewsById(long id) throws Exception;
    News saveNews(NewsDTO newsDTO);
    News updateNews(long id, NewsDTO newsDTO) throws Exception;
}
