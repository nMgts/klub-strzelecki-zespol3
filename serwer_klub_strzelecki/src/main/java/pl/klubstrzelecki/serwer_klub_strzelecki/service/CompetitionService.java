package pl.klubstrzelecki.serwer_klub_strzelecki.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.klubstrzelecki.serwer_klub_strzelecki.convert.CompetitionDTOMapper;
import pl.klubstrzelecki.serwer_klub_strzelecki.dto.CompetitionDTO;
import pl.klubstrzelecki.serwer_klub_strzelecki.dto.ReqRes;
import pl.klubstrzelecki.serwer_klub_strzelecki.model.Competition;
import pl.klubstrzelecki.serwer_klub_strzelecki.model.Shooter;
import pl.klubstrzelecki.serwer_klub_strzelecki.model.User;
import pl.klubstrzelecki.serwer_klub_strzelecki.repository.CompetitionRepository;
import pl.klubstrzelecki.serwer_klub_strzelecki.repository.ShooterRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class CompetitionService {
    private final CompetitionRepository competitionRepository;
    private final ShooterRepository shooterRepository;
    private final CompetitionDTOMapper competitionDTOMapper;

    @Autowired
    public CompetitionService(CompetitionRepository competitionRepository, ShooterRepository shooterRepository, CompetitionDTOMapper competitionDTOMapper) {
        this.competitionRepository = competitionRepository;
        this.shooterRepository = shooterRepository;
        this.competitionDTOMapper = competitionDTOMapper;
    }

    public List<CompetitionDTO> findAll() {
        List<Competition> competitions = competitionRepository.findAll();
        return competitions.stream()
                .map(competitionDTOMapper::convertCompetitionToCompetitionDTO)
                .collect(Collectors.toList());
    }

    public Competition saveCompetition(CompetitionDTO competitionDTO) {
        Competition competition = competitionDTOMapper.convertCompetitionDTOtoCompetition(competitionDTO);
        return competitionRepository.save(competition);
    }

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

    public void signUp(String email, long competitionId) throws Exception {
        try {
            Optional<Shooter> shooterOpt = shooterRepository.findByEmail(email);
            if (shooterOpt.isPresent()) {
                long shooterId = shooterOpt.get().getId();
                assignShooterToCompetition(competitionId, shooterId);
            } else {
                throw new Exception("Competition or Shooter not found");
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public void removeShooterFromCompetition(long competitionId, long shooterId) throws Exception {
        Optional<Competition> competitionOpt = competitionRepository.findById(competitionId);
        Optional<Shooter> shooterOpt = shooterRepository.findById(shooterId);

        if (competitionOpt.isPresent() && shooterOpt.isPresent()) {
            Competition competition = competitionOpt.get();
            Shooter shooter = shooterOpt.get();

            if (competition.getShooters().contains(shooter) && shooter.getCompetitions().contains(competition)) {
                competition.getShooters().remove(shooter);
                shooter.getCompetitions().remove(competition);

                shooterRepository.save(shooter);
                competitionRepository.save(competition);
            } else {
                throw new Exception("Shooter is not assigned to this competition");
            }
        } else {
            throw new Exception("Competition or Shooter not found");
        }
    }

    public void signOff(String email, long competitionId) throws Exception {
        try {
            Optional<Shooter> shooterOpt = shooterRepository.findByEmail(email);
            Optional<Competition> competitionOpt = competitionRepository.findById(competitionId);

            if (shooterOpt.isPresent() && competitionOpt.isPresent()) {
                Shooter shooter = shooterOpt.get();
                Competition competition = competitionOpt.get();

                if (competition.getShooters().contains(shooter)) {
                    competition.getShooters().remove(shooter);
                    shooter.getCompetitions().remove(competition);

                    shooterRepository.save(shooter);
                    competitionRepository.save(competition);
                } else {
                    throw new Exception("Shooter is not assigned to this competition");
                }
            } else {
                throw new Exception("Competition or Shooter not found");
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
