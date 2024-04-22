package pl.klubstrzelecki.serwer_klub_strzelecki.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.klubstrzelecki.serwer_klub_strzelecki.model.Competition;
import pl.klubstrzelecki.serwer_klub_strzelecki.repository.ComptetionRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("api/competition")
public class CompetitionController {

    @Autowired
    private ComptetionRepository comptetionRepository;

    @GetMapping("/all")
    public ResponseEntity<Object> getAllCompetitions() {
        return ResponseEntity.ok(comptetionRepository.findAll());
    }

    @PostMapping("/save")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Competition createCompetition(@RequestBody Competition competition) throws Exception {
        return comptetionRepository.save(competition);
    }
}
