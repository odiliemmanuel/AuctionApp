package com.emailsystem.emailer.producer;

import com.emailsystem.emailer.model.EmailEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class EmailEventProducer {

    private static final String TOPIC = "email-events";
    private  final KafkaTemplate<String, EmailEvent> kafkaTemplate;

    public EmailEventProducer(KafkaTemplate<String, EmailEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publish(EmailEvent emailEvent) {
        kafkaTemplate.send(TOPIC, emailEvent);
        System.out.println("Published email event for: " + emailEvent.to());
    }
}
