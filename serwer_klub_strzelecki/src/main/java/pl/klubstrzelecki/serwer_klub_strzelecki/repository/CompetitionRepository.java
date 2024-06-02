package pl.klubstrzelecki.serwer_klub_strzelecki.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.klubstrzelecki.serwer_klub_strzelecki.model.Competition;

import java.util.List;

@Repository
public interface CompetitionRepository extends JpaRepository<Competition, Long> {
}
