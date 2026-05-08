package com.emailsystem.emailer.consumer;

import com.emailsystem.emailer.model.EmailEvent;
import com.emailsystem.emailer.service.EmailSenderService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class EmailEventConsumer {

    private final EmailSenderService emailSenderService;


    public EmailEventConsumer(EmailSenderService emailSenderService) {
        this.emailSenderService = emailSenderService;
    }

    @KafkaListener(topics = "email-events",  groupId = "email-group")
    public void consume(EmailEvent emailEvent) {
        System.out.println("Received email event: " + emailEvent.to());
        emailSenderService.send(emailEvent);
    }
}

