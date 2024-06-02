package pl.klubstrzelecki.serwer_klub_strzelecki.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.klubstrzelecki.serwer_klub_strzelecki.convert.ShooterDTOMapper;
import pl.klubstrzelecki.serwer_klub_strzelecki.dto.ShooterDTO;
import pl.klubstrzelecki.serwer_klub_strzelecki.model.Competition;
import pl.klubstrzelecki.serwer_klub_strzelecki.model.Shooter;
import pl.klubstrzelecki.serwer_klub_strzelecki.model.User;
import pl.klubstrzelecki.serwer_klub_strzelecki.repository.CompetitionRepository;
import pl.klubstrzelecki.serwer_klub_strzelecki.repository.ShooterRepository;
import pl.klubstrzelecki.serwer_klub_strzelecki.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ShooterService {
    private final ShooterRepository shooterRepository;
    private final CompetitionRepository competitionRepository;
    private final ShooterDTOMapper shooterDTOMapper;
    private final UserRepository userRepository;

    @Autowired
    public ShooterService(ShooterRepository shooterRepository,  CompetitionRepository competitionRepository,
                          UserRepository userRepository, ShooterDTOMapper shooterDTOMapper ) {
        this.shooterRepository = shooterRepository;
        this.competitionRepository = competitionRepository;
        this.userRepository = userRepository;
        this.shooterDTOMapper = shooterDTOMapper;
    }

    public List<ShooterDTO> findAll() {
        List<Shooter> shooterList = shooterRepository.findAll();
        List<ShooterDTO> shooterDTOList = new ArrayList<>();
        for (Shooter shooter : shooterList) {
            User user = userRepository.findByShooter(shooter);
            shooter.setAssignedToUser(user != null);
            shooterDTOList.add(shooterDTOMapper.convertShooterToShooterDTO(shooter));
        }
        return shooterDTOList;
    }

    public ShooterDTO findShooterById(long shooterId) throws Exception {
        Optional<Shooter> opt = shooterRepository.findById(shooterId);
        if (opt.isPresent()) {
            Shooter shooter = opt.get();
            return shooterDTOMapper.convertShooterToShooterDTO(shooter);
        }
        throw new Exception("Shooter not found with id" + shooterId);
    }

    public Shooter saveShooter(ShooterDTO shooterDTO) {
        Shooter shooter = shooterDTOMapper.convertShooterDTOtoShooter(shooterDTO);
        return shooterRepository.save(shooter);
    }

    public Shooter updateShooter(long id, ShooterDTO shooterDTO) throws Exception {
        Optional<Shooter> shooterOptional = shooterRepository.findById(id);

        if (shooterOptional.isEmpty()) {
            throw new Exception("Shooter not found with id " + id);
        }

        Shooter existingShooter = shooterOptional.get();
        Shooter updatedShooter = shooterDTOMapper.convertShooterDTOtoShooter(shooterDTO);
        updatedShooter.setId(existingShooter.getId());

        return shooterRepository.save(updatedShooter);
    }

    public void deleteShooterById(long id) throws Exception {
        Optional<Shooter> opt = shooterRepository.findById(id);
        if (opt.isPresent()) {
            Shooter shooter = opt.get();
            removeShooterFromAllCompetitions(id);
            shooterRepository.delete(shooter);
        }
        else {
            throw new Exception("Shooter not found with id " + id);
        }
    }

    private void removeShooterFromAllCompetitions(long shooterId) throws Exception {
        Optional<Shooter> shooterOpt = shooterRepository.findById(shooterId);

        if (shooterOpt.isPresent()) {
            Shooter shooter = shooterOpt.get();
            List<Competition> competitions = new ArrayList<>(shooter.getCompetitions());

            for (Competition competition : competitions) {
                competition.getShooters().remove(shooter);
                competitionRepository.save(competition);
            }

            shooter.getCompetitions().clear();
            shooterRepository.save(shooter);
        } else {
            throw new Exception("Shooter not found");
        }
    }
}
