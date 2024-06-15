package pl.klubstrzelecki.serwer_klub_strzelecki.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import pl.klubstrzelecki.serwer_klub_strzelecki.dto.CompetitionDTO;
import pl.klubstrzelecki.serwer_klub_strzelecki.dto.ReqRes;
import pl.klubstrzelecki.serwer_klub_strzelecki.service.CompetitionService;

@RestController
@RequestMapping("/api/competition")
public class CompetitionController {
    private final CompetitionService competitionService;

    @Autowired
    public CompetitionController(CompetitionService competitionService) {
        this.competitionService = competitionService;
    }

    @GetMapping("/all")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public ResponseEntity<Object> getAllCompetitions() {
        return ResponseEntity.ok().body(competitionService.findAll());
    }

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Object> createCompetition(@RequestBody CompetitionDTO competitionDTO) {
        competitionService.saveCompetition(competitionDTO);
        return ResponseEntity.ok().body("{\"message\": \"Competition saved successfully!\"}");
    }

    @PostMapping("/assign/{competitionId}/{shooterId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Object> assignShooterToCompetition(@PathVariable long competitionId, @PathVariable long shooterId) {
        try {
            competitionService.assignShooterToCompetition(competitionId, shooterId);
            return ResponseEntity.ok().body("{\"message\": \"Shooter assigned to competition successfully!\"}");
        } catch (Exception e) {
            return ResponseEntity.status(404).body("{\"message\": \"" + e.getMessage() + "\"}");
        }
    }

    @PostMapping("/signup/{competitionId}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public void signUp(@PathVariable long competitionId) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        competitionService.signUp(email, competitionId);
    }

    @DeleteMapping("/remove/{competitionId}/{shooterId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Object> removeShooterFromCompetition(@PathVariable long competitionId, @PathVariable long shooterId) {
        try {
            competitionService.removeShooterFromCompetition(competitionId, shooterId);
            return ResponseEntity.ok().body("{\"message\": \"Shooter removed from competition successfully!\"}");
        } catch (Exception e) {
            return ResponseEntity.status(404).body("{\"message\": \"" + e.getMessage() + "\"}");
        }
    }

    @DeleteMapping("/signoff/{competitionId}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public void signOff(@PathVariable long competitionId) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        competitionService.signOff(email, competitionId);
    }
}
