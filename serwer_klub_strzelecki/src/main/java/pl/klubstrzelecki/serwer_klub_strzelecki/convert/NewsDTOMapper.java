package pl.klubstrzelecki.serwer_klub_strzelecki.convert;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.klubstrzelecki.serwer_klub_strzelecki.dto.NewsDTO;
import pl.klubstrzelecki.serwer_klub_strzelecki.model.News;

@Component
public class NewsDTOMapper {
    private final ModelMapper modelMapper;

    @Autowired
    public NewsDTOMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public NewsDTO convertNewsToNewsDTO(News news) {
        NewsDTO newsDTO = modelMapper.map(news, NewsDTO.class);
        return newsDTO;
    }

    public News convertNewsDTOtoNews(NewsDTO newsDTO) {
        News news = modelMapper.map(newsDTO, News.class);
        return news;
    }
}
