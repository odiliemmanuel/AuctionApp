package org.auctionsystem.AuctionSystem.event;

public class NewAuctionEvent extends Event{

    private String auctionId;
    private String emailAddress;


    public NewAuctionEvent(String auctionId,  String emailAddress) {
        super("EVT-" + auctionId, "NEW_AUCTION");
        this.auctionId = auctionId;
        this.emailAddress = emailAddress;
    }

    public String getAuctionId() {
        return auctionId;
    }


    public String getEmailAddress() {
        return emailAddress;
    }

}
