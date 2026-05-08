package com.emailsystem.emailer.model;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record NewAuctionEvent(
        String id,
        String type,
        String auctionId,
        String emailAddress,
        String firstName
) implements Event {


    public NewAuctionEvent() {
        this(null, null, null, null, null);
    }

    @Override
    public String getId() {
        return "";
    }

    @Override
    public String getType() {
        return "";
    }
}