package pl.klubstrzelecki.serwer_klub_strzelecki.migration;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.klubstrzelecki.serwer_klub_strzelecki.dto.NewsDTO;
import pl.klubstrzelecki.serwer_klub_strzelecki.model.MigrationInfo;
import pl.klubstrzelecki.serwer_klub_strzelecki.repository.MigrationInfoRepository;
import pl.klubstrzelecki.serwer_klub_strzelecki.service.NewsService;

import java.util.Date;

@Service
@AllArgsConstructor
public class NewsMigration {
    private final NewsService newsService;
    private final MigrationInfoRepository migrationInfoRepository;

    @PostConstruct
    public void init() {
        if (!migrationInfoRepository.existsByMigrationName("InitNewsMigration")) {
            addNewsEntries();

            MigrationInfo migrationInfo = new MigrationInfo();
            migrationInfo.setMigrationName("InitNewsMigration");
            migrationInfo.setExecutedAt(new Date());
            migrationInfoRepository.save(migrationInfo);
        }
    }

    private void addNewsEntries() {
        NewsDTO news1 = new NewsDTO(0, "Ogólnopolskie Zawody Strzeleckie 2024 już wkrótce!",
                "Z radością ogłaszamy, że nasz klub będzie gospodarzem Ogólnopolskich Zawodów Strzeleckich 2024! " +
                        "Wydarzenie odbędzie się w dniach 5-7 czerwca. Zawodnicy z całej Polski będą rywalizować w różnych " +
                        "kategoriach, w tym karabin, pistolet i strzelba. Zapraszamy wszystkich członków klubu do wzięcia " +
                        "udziału w tym ekscytującym wydarzeniu!");

        NewsDTO news2 = new NewsDTO(0, "Nowy sprzęt strzelecki w naszym klubie",
                "Nasz klub wzbogacił się o nowoczesny sprzęt strzelecki! Od dziś wszyscy członkowie mogą korzystać " +
                        "z nowych modeli karabinów sportowych oraz pistoletów. Sprzęt jest dostępny w naszej zbrojowni. " +
                        "Zapraszamy do testowania!");

        NewsDTO news3 = new NewsDTO(0, "Zimowy Turniej Strzelecki - zapisy ruszyły!",
                "Rozpoczęły się zapisy na Zimowy Turniej Strzelecki 2024! Turniej odbędzie się 15 grudnia. " +
                        "Kategorie obejmują zarówno karabin, jak i pistolet. Dla najlepszych zawodników przewidziano nagrody!");

        NewsDTO news4 = new NewsDTO(0, "Warsztaty strzeleckie dla początkujących",
                "Zapraszamy na warsztaty strzeleckie dla początkujących, które odbędą się w dniach 20-22 października. " +
                        "Szkolenie obejmuje podstawy obsługi broni, techniki strzeleckie oraz bezpieczeństwo na strzelnicy.");

        NewsDTO news5 = new NewsDTO(0, "Spotkanie integracyjne klubu strzeleckiego",
                "Zapraszamy wszystkich członków naszego klubu na spotkanie integracyjne, które odbędzie się 30 października. " +
                        "Celem spotkania jest zacieśnienie więzi między członkami oraz omówienie planów na przyszły rok!");

        newsService.saveNews(news1);
        newsService.saveNews(news2);
        newsService.saveNews(news3);
        newsService.saveNews(news4);
        newsService.saveNews(news5);
    }
}
