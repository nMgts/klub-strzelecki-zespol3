package pl.klubstrzelecki.serwer_klub_strzelecki.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.klubstrzelecki.serwer_klub_strzelecki.model.News;


@Repository
public interface NewsRepository extends JpaRepository<News, Long> {
    News findNewsById(Long id) throws Exception;
}




