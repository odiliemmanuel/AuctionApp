package com.emailsystem.emailer.consumer;


import com.emailsystem.emailer.model.CancelAuctionEvent;
import com.emailsystem.emailer.model.NewAuctionEvent;
import com.emailsystem.emailer.model.NewBidderEvent;
import com.emailsystem.emailer.model.NewUserEvent;
import com.emailsystem.emailer.service.EmailSenderService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Service;

@Service
public class AuctionEventConsumer {

    private final EmailSenderService emailSenderService;

    public AuctionEventConsumer(EmailSenderService emailSenderService) {
        this.emailSenderService = emailSenderService;
    }

    @RetryableTopic(
            attempts = "5",
            backoff = @Backoff(delay = 2000),
            autoCreateTopics = "true"
    )
    @KafkaListener(topics = "auction.events", groupId = "email-group")
    public void consume(NewAuctionEvent event, NewBidderEvent newBidderEvent, NewUserEvent newUserEvent, CancelAuctionEvent cancelAuctionEvent) {
        System.out.println("Received event type: " + event.type());

        switch (event.type()) {
            case "NEW_USER"       -> handleNewUser(newUserEvent);
            case "NEW_AUCTION"    -> handleNewAuction(event);
            case "CANCEL_AUCTION" -> handleCancelAuction(cancelAuctionEvent);
            case "NEW_BID"        -> handleNewBid(newBidderEvent);
            default -> System.out.println("Unknown event type: " + event.type());
        }
    }

    private void handleNewUser(NewUserEvent event) {
        emailSenderService.sendWelcomeEmail(event.emailAddress(), event.firstName());
    }

    private void handleNewAuction(NewAuctionEvent event) {
        emailSenderService.sendAuctionCreatedEmail(event.emailAddress(), event.auctionId());
    }

    private void handleCancelAuction(CancelAuctionEvent event) {
        emailSenderService.sendAuctionCancelledEmail(event.emailAddress(), event.auctionId());
    }

    private void handleNewBid(NewBidderEvent event) {
        emailSenderService.sendNewBidEmail(event.emailAddress(), event.auctionId());
    }
}