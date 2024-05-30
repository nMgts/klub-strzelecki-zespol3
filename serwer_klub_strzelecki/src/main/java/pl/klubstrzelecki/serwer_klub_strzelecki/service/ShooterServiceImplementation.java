package pl.klubstrzelecki.serwer_klub_strzelecki.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.klubstrzelecki.serwer_klub_strzelecki.dto.ShooterDTO;
import pl.klubstrzelecki.serwer_klub_strzelecki.model.Shooter;
import pl.klubstrzelecki.serwer_klub_strzelecki.repository.ShooterRepository;

import java.util.Optional;

@Service
public class ShooterServiceImplementation implements ShooterService {
    private final ShooterRepository shooterRepository;

    @Autowired
    public ShooterServiceImplementation(ShooterRepository shooterRepository) {
        this.shooterRepository = shooterRepository;
    }

    @Override
    public Shooter findShooterById(long shooterId) throws Exception {
        Optional<Shooter> opt = shooterRepository.findById(shooterId);

        if (opt.isPresent()) {
            return opt.get();
        }
        throw new Exception("Shooter not found with id" + shooterId);
    }

    public ShooterDTO saveShooter(ShooterDTO shooterDTO) {
        Shooter shooter = new Shooter();
        shooter.setId(shooterDTO.getId());
        shooter.setFirst_name(shooterDTO.getFirst_name());
        shooter.setLast_name(shooterDTO.getLast_name());
        shooter.setEmail(shooterDTO.getEmail());
        Shooter savedShooter = shooterRepository.save(shooter);
        return new ShooterDTO(savedShooter.getId(), savedShooter.getFirst_name(), savedShooter.getLast_name(),
                savedShooter.getEmail());
    }

    @Override
    public void deleteShooterById(Long id) {
        Optional<Shooter> opt = getShooterById(id);
        if (opt.isPresent()) {
            Shooter shooter = opt.get();
            shooterRepository.delete(shooter);
        }
        else {
            //todo
        }
    }

    private Optional<Shooter> getShooterById(Long id) {
        return shooterRepository.findById(id);
    }
}
