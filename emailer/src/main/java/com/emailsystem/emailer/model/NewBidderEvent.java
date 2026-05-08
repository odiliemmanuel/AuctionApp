package com.emailsystem.emailer.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record NewBidderEvent(
        String id,
        String type,
        String emailAddress,
        String auctionId,
        String amount
) implements Event{

    public NewBidderEvent(){
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


