package pl.klubstrzelecki.serwer_klub_strzelecki.service;

import pl.klubstrzelecki.serwer_klub_strzelecki.model.Shooter;

public interface ShooterService {
    Shooter findShooterById(long shooterId) throws Exception;
    void deleteShooterById(Long id);

    Shooter saveShooter(Shooter shooter);
}
