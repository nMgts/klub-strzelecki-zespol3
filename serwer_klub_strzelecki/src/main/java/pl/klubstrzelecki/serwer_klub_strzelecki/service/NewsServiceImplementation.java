package pl.klubstrzelecki.serwer_klub_strzelecki.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.klubstrzelecki.serwer_klub_strzelecki.model.News;
import pl.klubstrzelecki.serwer_klub_strzelecki.repository.NewsRepository;
import java.util.Optional;

@Service
public class NewsServiceImplementation implements NewsService {

    @Autowired
    private NewsRepository newsRepository;

    @Override
    public News findNewsById(long newsId) throws Exception {
        Optional<News> opt = newsRepository.findById(newsId);

        if (opt.isPresent()) {
            return opt.get();
        }
        throw new Exception("News not found with id" + newsId);
    }

}


