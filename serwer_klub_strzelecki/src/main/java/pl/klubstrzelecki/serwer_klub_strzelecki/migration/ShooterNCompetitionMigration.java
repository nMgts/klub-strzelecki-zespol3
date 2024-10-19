package pl.klubstrzelecki.serwer_klub_strzelecki.migration;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.klubstrzelecki.serwer_klub_strzelecki.dto.CompetitionDTO;
import pl.klubstrzelecki.serwer_klub_strzelecki.dto.ShooterDTO;
import pl.klubstrzelecki.serwer_klub_strzelecki.model.MigrationInfo;
import pl.klubstrzelecki.serwer_klub_strzelecki.repository.MigrationInfoRepository;
import pl.klubstrzelecki.serwer_klub_strzelecki.repository.ShooterRepository;
import pl.klubstrzelecki.serwer_klub_strzelecki.service.CompetitionService;
import pl.klubstrzelecki.serwer_klub_strzelecki.service.ShooterService;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class ShooterNCompetitionMigration {
    private final CompetitionService competitionService;
    private final ShooterService shooterService;
    private final ShooterRepository shooterRepository;
    private final MigrationInfoRepository migrationInfoRepository;

    @PostConstruct
    public void init() {
        if (!migrationInfoRepository.existsByMigrationName("ShooterCompetitionMigration")) {
            registerShooters();
            registerCompetitions();

            MigrationInfo migrationInfo = new MigrationInfo();
            migrationInfo.setMigrationName("ShooterCompetitionMigration");
            migrationInfo.setExecutedAt(new Date());
            migrationInfoRepository.save(migrationInfo);
        }
    }

    private void registerShooters() {
        List<ShooterDTO> shooters = Arrays.asList(
                new ShooterDTO(0, "Jan", "Kowalski", "strzelec1@example.com", false),
                new ShooterDTO(0, "Anna", "Nowak", "strzelec2@example.com", false),
                new ShooterDTO(0, "Piotr", "Zalewski", "strzelec3@example.com", false),
                new ShooterDTO(0, "Ewa", "Wojciechowska", "strzelec4@example.com", false),
                new ShooterDTO(0, "Krzysztof", "Michałowski", "strzelec5@example.com", false)
        );

        for (ShooterDTO shooterDTO : shooters) {
            if (shooterRepository.findByEmail(shooterDTO.getEmail()).isEmpty()) {
                shooterService.saveShooter(shooterDTO);
            }
        }
    }

    private void registerCompetitions() {
        CompetitionDTO competition1 = new CompetitionDTO(
                0,
                "Ogólnopolskie Zawody Strzeleckie 2024",
                "Zawody strzeleckie dla najlepszych strzelców z całej Polski.",
                LocalDateTime.of(2024, 6, 5, 10, 0),
                LocalDateTime.of(2024, 6, 7, 18, 0),
                100,
                Arrays.asList("strzelec1@example.com", "strzelec2@example.com")
        );

        CompetitionDTO competition2 = new CompetitionDTO(
                0,
                "Zimowy Turniej Strzelecki 2024",
                "Zimowy turniej dla strzelców.",
                LocalDateTime.of(2024, 12, 15, 9, 0),
                LocalDateTime.of(2024, 12, 15, 18, 0),
                50,
                Arrays.asList("strzelec3@example.com", "strzelec4@example.com")
        );

        CompetitionDTO competition3 = new CompetitionDTO(
                0,
                "Letnie Zawody Strzeleckie 2024",
                "Letnie zawody w strzelectwie sportowym.",
                LocalDateTime.of(2025, 7, 10, 10, 0),
                LocalDateTime.of(2025, 7, 12, 18, 0),
                80,
                Arrays.asList("strzelec5@example.com")
        );

        competitionService.saveCompetition(competition1);
        competitionService.saveCompetition(competition2);
        competitionService.saveCompetition(competition3);
    }
}
