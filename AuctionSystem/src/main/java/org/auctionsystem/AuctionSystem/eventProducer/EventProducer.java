package org.auctionsystem.AuctionSystem.eventProducer;

import org.auctionsystem.AuctionSystem.event.Event;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class EventProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private static final String TOPIC = "auction-events";

    public EventProducer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishEvent(Event event) {

        kafkaTemplate.send(TOPIC, event);
        System.out.println("Published email event for: " + event.getId());

    }

}