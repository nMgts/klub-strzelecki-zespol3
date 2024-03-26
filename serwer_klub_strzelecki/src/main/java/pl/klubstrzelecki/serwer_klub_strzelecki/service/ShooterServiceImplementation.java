package pl.klubstrzelecki.serwer_klub_strzelecki.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
}
