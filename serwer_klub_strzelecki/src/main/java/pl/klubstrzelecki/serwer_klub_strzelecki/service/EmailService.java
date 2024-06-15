package pl.klubstrzelecki.serwer_klub_strzelecki.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender emailSender;

    public EmailService(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void sendReminderEmail(String to, String competitionName) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Przypomnienie o zawodach");
        message.setText("Przypominamy o nadchodzących zawodach: " + competitionName);
        emailSender.send(message);
    }
}

