package pl.klubstrzelecki.serwer_klub_strzelecki.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.klubstrzelecki.serwer_klub_strzelecki.dto.NewsDTO;
import pl.klubstrzelecki.serwer_klub_strzelecki.model.News;
import pl.klubstrzelecki.serwer_klub_strzelecki.model.Shooter;
import pl.klubstrzelecki.serwer_klub_strzelecki.repository.ShooterRepository;

import java.util.Optional;

@Service
public class ShooterServiceImplementation implements ShooterService {

    @Autowired
    private ShooterRepository shooterRepository;

    @Override
    public Shooter findShooterById(long shooterId) throws Exception {
        Optional<Shooter> opt = shooterRepository.findById(shooterId);

        if (opt.isPresent()) {
            return opt.get();
        }
        throw new Exception("Shooter not found with id" + shooterId);
    }

    public Shooter saveShooter(Shooter shooter) {
        shooter = new Shooter();
        shooter.setId(shooter.getId());
        shooter.setFirstName(shooter.getFirstName());
        shooter.setLastName(shooter.getLastName());
        shooter.setEmail(shooter.getEmail());
        Shooter savedShooter = shooterRepository.save(shooter);
        return new Shooter(savedShooter.getId(), savedShooter.getFirstName(), savedShooter.getLastName(),
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
