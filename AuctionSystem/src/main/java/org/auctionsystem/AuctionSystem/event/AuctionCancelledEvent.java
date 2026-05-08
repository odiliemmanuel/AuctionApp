package org.auctionsystem.AuctionSystem.event;

public class AuctionCancelledEvent extends Event{

    private String emailAddress;
    private String auctionId;

    public AuctionCancelledEvent(String auctionId,  String emailAddress) {
        super("EVT-" + auctionId, "CANCEL_AUCTION");
        this.auctionId = auctionId;
        this.emailAddress = emailAddress;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getAuctionId() {
        return auctionId;
    }
}
