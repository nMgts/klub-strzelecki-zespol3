package pl.klubstrzelecki.serwer_klub_strzelecki.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.klubstrzelecki.serwer_klub_strzelecki.convert.WeaponDTOMapper;
import pl.klubstrzelecki.serwer_klub_strzelecki.dto.WeaponDTO;
import pl.klubstrzelecki.serwer_klub_strzelecki.repository.WeaponRepository;
import pl.klubstrzelecki.serwer_klub_strzelecki.model.Weapon;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WeaponService {
    private final WeaponRepository weaponRepository;
    private final WeaponDTOMapper weaponDTOMapper;

    @Autowired
    public WeaponService(WeaponRepository weaponRepository, WeaponDTOMapper weaponDTOMapper) {
        this.weaponRepository = weaponRepository;
        this.weaponDTOMapper = weaponDTOMapper;
    }

    public List<WeaponDTO> findAll() {
        List<Weapon> weaponList = weaponRepository.findAll();
        List<WeaponDTO> weaponDTOList = new ArrayList<>();
        for (Weapon weapon : weaponList) {
            weaponDTOList.add(weaponDTOMapper.convertWeaponToWeaponDTO(weapon));
        }
        return weaponDTOList;
    }

    public WeaponDTO findWeaponById(long weaponId) throws Exception {
        Optional<Weapon> opt = weaponRepository.findById(weaponId);
        if (opt.isPresent()) {
            Weapon weapon = opt.get();
            return weaponDTOMapper.convertWeaponToWeaponDTO(weapon);
        }
        throw new Exception("Weapon not found with id " + weaponId);
    }

    public Weapon saveWeapon(WeaponDTO weaponDTO, MultipartFile image) {
        Weapon weapon = weaponDTOMapper.convertWeaponDTOtoWeapon(weaponDTO);
        return weaponRepository.save(weapon);
    }

    public Weapon updateWeapon(long id, WeaponDTO weaponDTO) throws Exception {
        Optional<Weapon> weaponOptional = weaponRepository.findById(id);

        if (weaponOptional.isEmpty()) {
            throw new Exception("Weapon not found with id " + id);
        }

        Weapon existingWeapon = weaponOptional.get();
        Weapon updatedWeapon = weaponDTOMapper.convertWeaponDTOtoWeapon(weaponDTO);
        updatedWeapon.setId(existingWeapon.getId());

        return weaponRepository.save(updatedWeapon);
    }

    public void deleteWeaponById(long id) throws Exception {
        Optional<Weapon> opt = weaponRepository.findById(id);
        if (opt.isPresent()) {
            Weapon weapon = opt.get();
            weaponRepository.delete(weapon);
        }
        else {
            throw new Exception("Weapon not found with id " + id);
        }
    }

    public WeaponDTO addWeapon(WeaponDTO weaponDTO, MultipartFile image) throws IOException {
        try {
            Weapon weapon = weaponDTOMapper.convertWeaponDTOtoWeapon(weaponDTO);

            // Jeśli obraz jest przesłany, zapisz go jako bajty
            if (image != null && !image.isEmpty()) {
                weapon.setImage(image.getBytes());
            }

            weaponRepository.save(weapon);
            return weaponDTOMapper.convertWeaponToWeaponDTO(weapon);  // Zwraca DTO po zapisaniu
        } catch (IOException e) {
            throw new IOException("Failed to process the image file", e);
        }
    }

}
