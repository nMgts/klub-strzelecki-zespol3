package pl.klubstrzelecki.serwer_klub_strzelecki.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.klubstrzelecki.serwer_klub_strzelecki.model.Competition;
import pl.klubstrzelecki.serwer_klub_strzelecki.model.Shooter;
import pl.klubstrzelecki.serwer_klub_strzelecki.repository.CompetitionRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class ReminderService {
    private final CompetitionRepository competitionRepository;
    private final EmailService emailService; // serwis do wysyłania e-maili

    public void sendReminders() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime twentyFourHoursLater = now.plusHours(24);

        List<Competition> competitions = competitionRepository.findCompetitionsForReminder(now, twentyFourHoursLater);

        for (Competition competition : competitions) {
            if (!competition.isReminder_sent()) {
                sendReminderEmails(competition);
                competition.setReminder_sent(true);
                competitionRepository.save(competition);
            }
        }
    }

    private void sendReminderEmails(Competition competition) {
        for (Shooter shooter : competition.getShooters()) {
            emailService.sendReminderEmail(shooter.getEmail(), competition.getName());
        }
    }
}
