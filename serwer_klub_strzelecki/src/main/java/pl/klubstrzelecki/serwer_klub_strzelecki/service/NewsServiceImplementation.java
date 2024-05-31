package pl.klubstrzelecki.serwer_klub_strzelecki.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.klubstrzelecki.serwer_klub_strzelecki.convert.NewsDTOMapper;
import pl.klubstrzelecki.serwer_klub_strzelecki.dto.NewsDTO;
import pl.klubstrzelecki.serwer_klub_strzelecki.model.News;
import pl.klubstrzelecki.serwer_klub_strzelecki.repository.NewsRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NewsServiceImplementation implements NewsService {
    private final NewsRepository newsRepository;
    private final NewsDTOMapper newsDTOMapper;

    @Autowired
    public NewsServiceImplementation(NewsRepository newsRepository, NewsDTOMapper newsDTOMapper) {
        this.newsRepository = newsRepository;
        this.newsDTOMapper = newsDTOMapper;
    }

    @Override
    public List<NewsDTO> findAll() {
        List<News> newsList = newsRepository.findAll();
        List<NewsDTO> newsDTOList = new ArrayList<>();
        for (News news : newsList) {
            newsDTOList.add(newsDTOMapper.convertNewsToNewsDTO(news));
        }
        return newsDTOList;
    }

    @Override
    public NewsDTO findNewsById(long newsId) throws Exception {
        Optional<News> opt = newsRepository.findById(newsId);
        if (opt.isPresent()) {
            News news = opt.get();
            return newsDTOMapper.convertNewsToNewsDTO(news);
        }
        throw new Exception("News not found with id " + newsId);
    }

    @Override
    public News saveNews(NewsDTO newsDTO) {
        News news = newsDTOMapper.convertNewsDTOtoNews(newsDTO);
        return newsRepository.save(news);
    }

    @Override
    public News updateNews(long id, NewsDTO newsDTO) throws Exception {
        Optional<News> newsOptional = newsRepository.findById(id);

        if (newsOptional.isEmpty()) {
            throw new Exception("News not found with id " + id);
        }

        News existingNews = newsOptional.get();
        News updatedNews = newsDTOMapper.convertNewsDTOtoNews(newsDTO);
        updatedNews.setId(existingNews.getId());

        return newsRepository.save(updatedNews);
    }

    @Override
    public void deleteNewsById(Long id) throws Exception {
        Optional<News> opt = getNewsById(id);
        if (opt.isPresent()) {
            News news = opt.get();
            newsRepository.delete(news);
        }
        else {
            throw new Exception("News not found with id " + id);
        }
    }

    public Optional<News> getNewsById(Long id) {
        return newsRepository.findById(id);
    }
}
