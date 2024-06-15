package pl.klubstrzelecki.serwer_klub_strzelecki.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.klubstrzelecki.serwer_klub_strzelecki.service.ReminderService;

@RestController
@RequestMapping("/api/remind")
public class ReminderController {
    private final ReminderService reminderService;

    @Autowired
    public ReminderController(ReminderService reminderService) {
        this.reminderService = reminderService;
    }

    @PostMapping("/send")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String sendReminder() {
        reminderService.sendReminders();

        return "Reminder sent successfully!";
    }

}
