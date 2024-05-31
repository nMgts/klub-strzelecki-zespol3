package pl.klubstrzelecki.serwer_klub_strzelecki.service;

import pl.klubstrzelecki.serwer_klub_strzelecki.dto.ShooterDTO;
import pl.klubstrzelecki.serwer_klub_strzelecki.model.News;
import pl.klubstrzelecki.serwer_klub_strzelecki.model.Shooter;

import java.util.List;

public interface ShooterService {
    List<ShooterDTO> findAll();
    ShooterDTO findShooterById(long id) throws Exception;
    void deleteShooterById(long id) throws Exception;

    Shooter saveShooter(ShooterDTO shooterDTO);
    Shooter updateShooter(long id, ShooterDTO shooterDTO) throws Exception;
}
