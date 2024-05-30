package pl.klubstrzelecki.serwer_klub_strzelecki.service;

import org.springframework.http.ResponseEntity;

public interface CompetitionService {
    ResponseEntity<Object> registerUserToCompetition(long competitionId, long userId);
}
