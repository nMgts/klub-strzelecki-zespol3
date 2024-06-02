package pl.klubstrzelecki.serwer_klub_strzelecki.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.klubstrzelecki.serwer_klub_strzelecki.model.Shooter;
import pl.klubstrzelecki.serwer_klub_strzelecki.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShooterRepository extends JpaRepository<Shooter, Long> {
    Optional<Shooter> findByEmail(String email);
}
