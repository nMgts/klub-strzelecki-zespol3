package pl.klubstrzelecki.serwer_klub_strzelecki.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.klubstrzelecki.serwer_klub_strzelecki.convert.CompetitionDTOMapper;
import pl.klubstrzelecki.serwer_klub_strzelecki.dto.CompetitionDTO;
import pl.klubstrzelecki.serwer_klub_strzelecki.model.Competition;
import pl.klubstrzelecki.serwer_klub_strzelecki.model.Shooter;
import pl.klubstrzelecki.serwer_klub_strzelecki.repository.CompetitionRepository;
import pl.klubstrzelecki.serwer_klub_strzelecki.repository.ShooterRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class CompetitionServiceImplementation implements CompetitionService {
    private final CompetitionRepository competitionRepository;
    private final ShooterRepository shooterRepository;
    private final CompetitionDTOMapper competitionDTOMapper;

    @Autowired
    public CompetitionServiceImplementation(CompetitionRepository competitionRepository, ShooterRepository shooterRepository, CompetitionDTOMapper competitionDTOMapper) {
        this.competitionRepository = competitionRepository;
        this.shooterRepository = shooterRepository;
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

    @Override
    public void assignShooterToCompetition(long competitionId, long shooterId) throws Exception {
        Optional<Competition> competitionOpt = competitionRepository.findById(competitionId);
        Optional<Shooter> shooterOpt = shooterRepository.findById(shooterId);

        if (competitionOpt.isPresent() && shooterOpt.isPresent()) {
            Competition competition = competitionOpt.get();
            Shooter shooter = shooterOpt.get();

            competition.getShooters().add(shooter);
            shooter.getCompetitions().add(competition);

            shooterRepository.save(shooter);
            competitionRepository.save(competition);
        } else {
            throw new Exception("Competition or Shooter not found");
        }
    }
}
