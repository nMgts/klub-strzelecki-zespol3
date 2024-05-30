package pl.klubstrzelecki.serwer_klub_strzelecki.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.klubstrzelecki.serwer_klub_strzelecki.model.Competition;
import pl.klubstrzelecki.serwer_klub_strzelecki.model.User;
import pl.klubstrzelecki.serwer_klub_strzelecki.repository.CompetitionRepository;
import pl.klubstrzelecki.serwer_klub_strzelecki.repository.UserRepository;

import java.util.Optional;


@Service
public class CompetitionServiceImplementation implements CompetitionService {
    private final CompetitionRepository competitionRepository;
    private final UserRepository userRepository;

    @Autowired
    public CompetitionServiceImplementation(CompetitionRepository competitionRepository, UserRepository userRepository) {
        this.competitionRepository = competitionRepository;
        this.userRepository = userRepository;
    }

    public ResponseEntity<Object> registerUserToCompetition(long competitionId, long userId) {
        Optional<Competition> competitionOptional = competitionRepository.findById(competitionId);
        Optional<User> userOptional = userRepository.findById(userId);

        if (competitionOptional.isPresent() && userOptional.isPresent()) {
            Competition competition = competitionOptional.get();
            User user = userOptional.get();

            competition.getUsers().add(user);
            competitionRepository.save(competition);

            return ResponseEntity.ok("User registered successfully to competition.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
