package pl.klubstrzelecki.serwer_klub_strzelecki.service;

import pl.klubstrzelecki.serwer_klub_strzelecki.dto.ShooterDTO;
import pl.klubstrzelecki.serwer_klub_strzelecki.model.Shooter;

public interface ShooterService {
    Shooter findShooterById(long shooterId) throws Exception;
    void deleteShooterById(Long id);

    ShooterDTO saveShooter(ShooterDTO shooter);
}
