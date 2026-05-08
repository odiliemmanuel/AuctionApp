package com.emailsystem.emailer.controller;

import com.emailsystem.emailer.model.EmailEvent;
import com.emailsystem.emailer.producer.EmailEventProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/send/email")
public class EmailController {

    private final EmailEventProducer emailEventProducer;

    public EmailController(EmailEventProducer emailEventProducer) {
        this.emailEventProducer = emailEventProducer;
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendEmail(@RequestBody EmailEvent emailEvent) {
        emailEventProducer.publish(emailEvent);
        return ResponseEntity.ok("Email has been sent");
    }
}
