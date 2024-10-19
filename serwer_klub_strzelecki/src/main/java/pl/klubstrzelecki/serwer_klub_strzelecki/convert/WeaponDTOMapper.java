package pl.klubstrzelecki.serwer_klub_strzelecki.convert;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.klubstrzelecki.serwer_klub_strzelecki.dto.NewsDTO;
import pl.klubstrzelecki.serwer_klub_strzelecki.dto.WeaponDTO;
import pl.klubstrzelecki.serwer_klub_strzelecki.model.News;
import pl.klubstrzelecki.serwer_klub_strzelecki.model.Weapon;

import java.util.Base64;

@Component
public class WeaponDTOMapper {
    private final ModelMapper modelMapper;

    @Autowired
    public WeaponDTOMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public WeaponDTO convertWeaponToWeaponDTO(Weapon weapon) {
        WeaponDTO weaponDTO = modelMapper.map(weapon, WeaponDTO.class);
        return weaponDTO;
    }

    public Weapon convertWeaponDTOtoWeapon(WeaponDTO weaponDTO) {
        Weapon weapon = modelMapper.map(weaponDTO, Weapon.class);
        return weapon;
    }

    @Mapper(componentModel = "spring")
    public interface WeaponDTOMapper {

        @Mapping(target = "image", source = "image", qualifiedByName = "byteArrayToBase64")
        WeaponDTO convertWeaponToWeaponDTO(Weapon weapon);

        @Mapping(target = "image", source = "image", qualifiedByName = "base64ToByteArray")
        Weapon convertWeaponDTOtoWeapon(WeaponDTO weaponDTO);

        // Konwersja obrazu z bajtów na Base64
        @Named("byteArrayToBase64")
        default String byteArrayToBase64(byte[] image) {
            if (image != null) {
                return "data:image/png;base64," + Base64.getEncoder().encodeToString(image);
            }
            return null;
        }

        // Konwersja obrazu z Base64 na bajty
        @Named("base64ToByteArray")
        default byte[] base64ToByteArray(String image) {
            if (image != null && image.startsWith("data:image/png;base64,")) {
                String base64Image = image.substring("data:image/png;base64,".length());
                return Base64.getDecoder().decode(base64Image);
            }
            return null;
        }
    }
}

