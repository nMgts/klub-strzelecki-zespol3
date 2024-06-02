package pl.klubstrzelecki.serwer_klub_strzelecki.convert;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.klubstrzelecki.serwer_klub_strzelecki.dto.ShooterDTO;
import pl.klubstrzelecki.serwer_klub_strzelecki.model.Shooter;

@Component
public class ShooterDTOMapper {
    private final ModelMapper modelMapper;

    @Autowired
    public ShooterDTOMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public ShooterDTO convertShooterToShooterDTO(Shooter shooter) {
        ShooterDTO shooterDTO = modelMapper.map(shooter, ShooterDTO.class);
        return shooterDTO;
    }

    public Shooter convertShooterDTOtoShooter(ShooterDTO shooterDTO) {
        Shooter shooter = modelMapper.map(shooterDTO, Shooter.class);
        return shooter;
    }
}
