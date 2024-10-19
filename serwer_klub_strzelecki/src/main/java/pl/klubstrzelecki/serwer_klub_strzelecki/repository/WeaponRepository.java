package pl.klubstrzelecki.serwer_klub_strzelecki.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.klubstrzelecki.serwer_klub_strzelecki.model.Weapon;

@Repository
public interface WeaponRepository extends JpaRepository<Weapon, Long> {
}
