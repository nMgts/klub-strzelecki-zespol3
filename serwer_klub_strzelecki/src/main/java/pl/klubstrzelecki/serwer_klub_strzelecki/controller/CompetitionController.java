package pl.klubstrzelecki.serwer_klub_strzelecki.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.klubstrzelecki.serwer_klub_strzelecki.model.Competition;
import pl.klubstrzelecki.serwer_klub_strzelecki.repository.CompetitionRepository;
import pl.klubstrzelecki.serwer_klub_strzelecki.service.CompetitionService;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("api/competition")
public class CompetitionController {

    private final CompetitionRepository competitionRepository;
    private final CompetitionService competitionService;

    @Autowired
    public CompetitionController(CompetitionService competitionService, CompetitionRepository competitionRepository) {
        this.competitionRepository = competitionRepository;
        this.competitionService = competitionService;
    }

    @GetMapping("/all")
    public ResponseEntity<Object> getAllCompetitions() {
        return ResponseEntity.ok().body(competitionRepository.findAll());
    }

    @PostMapping("/add")
    //@PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Object> createCompetition(@RequestBody Competition competition) {
        return ResponseEntity.ok().body(competitionRepository.save(competition));
    }

    //@PostMapping("/{competitionId}/register/{userId}")
    public ResponseEntity<Object> registerUserToCompetition(@PathVariable long competitionId, @PathVariable long userId) {
        return competitionService.registerUserToCompetition(competitionId, userId);
    }
}
