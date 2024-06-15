package pl.klubstrzelecki.serwer_klub_strzelecki.config;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.klubstrzelecki.serwer_klub_strzelecki.service.ReminderService;

@Component
public class ReminderConfig {
    private final ReminderService reminderService;

    public ReminderConfig(ReminderService reminderService) {
        this.reminderService = reminderService;
    }

    @Scheduled(cron = "0 0 9 * * *") // Uruchamiaj codziennie o 9:00 rano
    public void scheduleReminders() {
        reminderService.sendReminders();
    }

    /*
    @Scheduled(cron = "0 2 * * * *") // Uruchamiaj co 2 minuty
    public void scheduleReminders() {
        reminderService.sendReminders();
    }
    */
}
