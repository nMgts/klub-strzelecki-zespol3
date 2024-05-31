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

    public NewsDTO saveNews(NewsDTO newsDTO) {
        News news = new News();
        news.setId(newsDTO.getId());
        news.setTitle(newsDTO.getTitle());
        news.setContent(newsDTO.getContent());
        News savedNews = newsRepository.save(news);
        return new NewsDTO(savedNews.getId(), savedNews.getTitle(), savedNews.getContent());
    }

    @Override
    public void deleteNewsById(Long id) {
        Optional<News> opt = getNewsById(id);
        if (opt.isPresent()) {
            News news = opt.get();
            newsRepository.delete(news);
        }
        else {
            //todo
        }
    }

    public Optional<News> getNewsById(Long id) {
        return newsRepository.findById(id);
    }
}
