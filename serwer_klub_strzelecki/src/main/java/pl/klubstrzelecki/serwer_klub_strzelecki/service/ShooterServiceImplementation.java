package pl.klubstrzelecki.serwer_klub_strzelecki.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.klubstrzelecki.serwer_klub_strzelecki.convert.ShooterDTOMapper;
import pl.klubstrzelecki.serwer_klub_strzelecki.dto.ShooterDTO;
import pl.klubstrzelecki.serwer_klub_strzelecki.model.Shooter;
import pl.klubstrzelecki.serwer_klub_strzelecki.repository.ShooterRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ShooterServiceImplementation implements ShooterService {
    private final ShooterRepository shooterRepository;
    private final ShooterDTOMapper shooterDTOMapper;

    @Autowired
    public ShooterServiceImplementation(ShooterRepository shooterRepository, ShooterDTOMapper shooterDTOMapper) {
        this.shooterRepository = shooterRepository;
        this.shooterDTOMapper = shooterDTOMapper;
    }

    @Override
    public List<ShooterDTO> findAll() {
        List<Shooter> shooterList = shooterRepository.findAll();
        List<ShooterDTO> shooterDTOList = new ArrayList<>();
        for (Shooter shooter : shooterList) {
            shooterDTOList.add(shooterDTOMapper.convertShooterToShooterDTO(shooter));
        }
        return shooterDTOList;
    }

    @Override
    public ShooterDTO findShooterById(long shooterId) throws Exception {
        Optional<Shooter> opt = shooterRepository.findById(shooterId);
        if (opt.isPresent()) {
            Shooter shooter = opt.get();
            return shooterDTOMapper.convertShooterToShooterDTO(shooter);
        }
        throw new Exception("Shooter not found with id" + shooterId);
    }

    @Override
    public Shooter saveShooter(ShooterDTO shooterDTO) {
        Shooter shooter = shooterDTOMapper.convertShooterDTOtoShooter(shooterDTO);
        return shooterRepository.save(shooter);
    }

    @Override
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

    @Override
    public void deleteShooterById(long id) throws Exception {
        Optional<Shooter> opt = shooterRepository.findById(id);
        if (opt.isPresent()) {
            Shooter shooter = opt.get();
            shooterRepository.delete(shooter);
        }
        else {
            throw new Exception("Shooter not found with id " + id);
        }
    }
}
