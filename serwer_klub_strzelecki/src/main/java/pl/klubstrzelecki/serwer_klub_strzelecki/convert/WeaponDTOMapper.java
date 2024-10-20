package pl.klubstrzelecki.serwer_klub_strzelecki.convert;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.klubstrzelecki.serwer_klub_strzelecki.dto.WeaponDTO;
import pl.klubstrzelecki.serwer_klub_strzelecki.model.Weapon;

import java.util.Base64;

@Component
public class WeaponDTOMapper {
    private final ModelMapper modelMapper;

    @Autowired
    public WeaponDTOMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    // Konwersja Weapon -> WeaponDTO
    public WeaponDTO convertWeaponToWeaponDTO(Weapon weapon) {
        WeaponDTO weaponDTO = modelMapper.map(weapon, WeaponDTO.class);
        if (weapon.getImage() != null) {
            weaponDTO.setImage(encodeImageToBase64(weapon.getImage())); // Konwersja byte[] na Base64 string
        }
        return weaponDTO;
    }

    // Konwersja WeaponDTO -> Weapon
    public Weapon convertWeaponDTOtoWeapon(WeaponDTO weaponDTO) {
        Weapon weapon = modelMapper.map(weaponDTO, Weapon.class);
        if (weaponDTO.getImage() != null) {
            weapon.setImage(decodeBase64ToImage(weaponDTO.getImage())); // Konwersja Base64 string na byte[]
        }
        return weapon;
    }

    // Metoda do konwersji byte[] na Base64
    private String encodeImageToBase64(byte[] image) {
        return "data:image/png;base64," + Base64.getEncoder().encodeToString(image);
    }

    // Metoda do konwersji Base64 na byte[]
    private byte[] decodeBase64ToImage(String imageBase64) {
        if (imageBase64 != null && imageBase64.startsWith("data:image/png;base64,")) {
            String base64Image = imageBase64.substring("data:image/png;base64,".length());
            return Base64.getDecoder().decode(base64Image);
        }
        return null;
    }
}
