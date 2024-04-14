package pl.klubstrzelecki.serwer_klub_strzelecki.service;

import pl.klubstrzelecki.serwer_klub_strzelecki.model.News;

public interface NewsService {
    News findNewsById(long newsId) throws Exception;

}








