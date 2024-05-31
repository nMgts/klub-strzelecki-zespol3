package pl.klubstrzelecki.serwer_klub_strzelecki.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.klubstrzelecki.serwer_klub_strzelecki.convert.CompetitionDTOMapper;
import pl.klubstrzelecki.serwer_klub_strzelecki.dto.CompetitionDTO;
import pl.klubstrzelecki.serwer_klub_strzelecki.dto.NewsDTO;
import pl.klubstrzelecki.serwer_klub_strzelecki.model.Competition;
import pl.klubstrzelecki.serwer_klub_strzelecki.model.News;
import pl.klubstrzelecki.serwer_klub_strzelecki.model.User;
import pl.klubstrzelecki.serwer_klub_strzelecki.repository.CompetitionRepository;
import pl.klubstrzelecki.serwer_klub_strzelecki.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class CompetitionServiceImplementation implements CompetitionService {
    private final CompetitionRepository competitionRepository;
    private final UserRepository userRepository;
    private final CompetitionDTOMapper competitionDTOMapper;

    @Autowired
    public CompetitionServiceImplementation(CompetitionRepository competitionRepository, UserRepository userRepository, CompetitionDTOMapper competitionDTOMapper) {
        this.competitionRepository = competitionRepository;
        this.userRepository = userRepository;
        this.competitionDTOMapper = competitionDTOMapper;
    }

    @Override
    public List<CompetitionDTO> findAll() {
        List<Competition> competitionList = competitionRepository.findAll();
        List<CompetitionDTO> competitionDTOList = new ArrayList<>();
        for (Competition competition : competitionList) {
            competitionDTOList.add(competitionDTOMapper.convertCompetitionToCompetitionDTO(competition));
        }
        return competitionDTOList;
    }

    @Override
    public Competition saveCompetition(CompetitionDTO competitionDTO) {
        Competition competition = competitionDTOMapper.convertCompetitionDTOtoCompetition(competitionDTO);
        return competitionRepository.save(competition);
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
