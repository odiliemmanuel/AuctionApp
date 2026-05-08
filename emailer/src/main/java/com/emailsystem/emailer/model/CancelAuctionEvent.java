package com.emailsystem.emailer.model;

public record CancelAuctionEvent(
        String id,
        String type,
        String auctionId,
        String emailAddress
) implements Event {

    public CancelAuctionEvent(){
        this(null, null, null, null);
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
