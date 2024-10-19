package pl.klubstrzelecki.serwer_klub_strzelecki.migration;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.klubstrzelecki.serwer_klub_strzelecki.dto.ReqRes;
import pl.klubstrzelecki.serwer_klub_strzelecki.dto.UserDTO;
import pl.klubstrzelecki.serwer_klub_strzelecki.model.MigrationInfo;
import pl.klubstrzelecki.serwer_klub_strzelecki.repository.MigrationInfoRepository;
import pl.klubstrzelecki.serwer_klub_strzelecki.service.ShooterService;
import pl.klubstrzelecki.serwer_klub_strzelecki.service.UserManagementService;
import pl.klubstrzelecki.serwer_klub_strzelecki.service.UserService;

import javax.annotation.PostConstruct;
import java.util.Date;

@Service
@AllArgsConstructor
public class InitMigration {
    private final UserManagementService userManagementService;
    private final MigrationInfoRepository migrationInfoRepository;

    @PostConstruct
    public void init() {
        if (!migrationInfoRepository.existsByMigrationName("InitMigration")) {
            registerTestUsers();

            MigrationInfo migrationInfo = new MigrationInfo();
            migrationInfo.setMigrationName("InitMigration");
            migrationInfo.setExecutedAt(new Date());
            migrationInfoRepository.save(migrationInfo);
        }
    }

    private void registerTestUsers() {
        ReqRes user1 = new ReqRes();
        user1.setFirst_name("Norbert");
        user1.setLast_name("Klessen");
        user1.setEmail("piorun0708@gmail.com");
        user1.setRole("ADMIN");
        user1.setPassword("123");

        ReqRes user2 = new ReqRes();
        user2.setFirst_name("User");
        user2.setLast_name("User");
        user2.setEmail("user@example.com");
        user2.setRole("USER");
        user2.setPassword("123");

        ReqRes user3 = new ReqRes();
        user3.setFirst_name("Admin");
        user3.setLast_name("Admin");
        user3.setEmail("admin@example.com");
        user3.setRole("ADMIN");
        user3.setPassword("123");

        userManagementService.register(user1);
        userManagementService.register(user2);
        userManagementService.register(user3);
    }
}
