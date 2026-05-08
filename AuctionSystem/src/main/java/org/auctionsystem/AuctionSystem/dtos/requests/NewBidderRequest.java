package org.auctionsystem.AuctionSystem.dtos.requests;

import lombok.Data;

@Data
public class NewBidderRequest {

    private String userId;
    private String auctionId;
    private String amount;
}
