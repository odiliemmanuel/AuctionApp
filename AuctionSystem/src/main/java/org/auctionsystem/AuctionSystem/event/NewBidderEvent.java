package org.auctionsystem.AuctionSystem.event;

public class NewBidderEvent extends Event{

    private String emailAddress;
    private String auctionId;
    private String bidId;
    private double amount;

    public NewBidderEvent(String bidId, double amount, String emailAddress, String auctionId) {
        super("EVT-" + bidId, "NEW_BID");
        this.bidId = bidId;
        this.amount = amount;
        this.emailAddress = emailAddress;
        this.auctionId = auctionId;
    }



    public double getAmount() {
        return amount;
    }

    public String getBidId() {
        return bidId;
    }

    public String getAuctionId() {
        return auctionId;
    }

    public String getEmailAddress() {
        return emailAddress;
    }
}
