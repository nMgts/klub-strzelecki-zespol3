package pl.klubstrzelecki.serwer_klub_strzelecki.migration;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.klubstrzelecki.serwer_klub_strzelecki.dto.WeaponDTO;
import pl.klubstrzelecki.serwer_klub_strzelecki.model.MigrationInfo;
import pl.klubstrzelecki.serwer_klub_strzelecki.repository.MigrationInfoRepository;
import pl.klubstrzelecki.serwer_klub_strzelecki.service.WeaponService;

import java.util.Date;

@Service
@AllArgsConstructor
public class WeaponMigration {
    private final WeaponService weaponService;
    private final MigrationInfoRepository migrationInfoRepository;

    @PostConstruct
    public void init() {
        if (!migrationInfoRepository.existsByMigrationName("InitWeaponMigration")) {
            //addWeaponEntries();

            MigrationInfo migrationInfo = new MigrationInfo();
            migrationInfo.setMigrationName("InitWeaponMigration");
            migrationInfo.setExecutedAt(new Date());
            //migrationInfoRepository.save(migrationInfo);
        }
    }
/*
    private void addWeaponEntries() {
        WeaponDTO weapon1 = new WeaponDTO(0, "AR15", "Karabinek", 5.56, "brak");
        WeaponDTO weapon2 = new WeaponDTO(1, "HK MR223", "Karabinek", 5.56, "brak");
    }

 */
}
