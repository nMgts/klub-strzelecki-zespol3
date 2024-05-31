package pl.klubstrzelecki.serwer_klub_strzelecki.service;

import org.springframework.http.ResponseEntity;
import pl.klubstrzelecki.serwer_klub_strzelecki.dto.CompetitionDTO;
import pl.klubstrzelecki.serwer_klub_strzelecki.model.Competition;

import java.util.List;

public interface CompetitionService {
    List<CompetitionDTO> findAll();
    Competition saveCompetition(CompetitionDTO competitionDTO);
    void assignShooterToCompetition(long competitionId, long shooterId) throws Exception;
}
