package org.auctionsystem.AuctionSystem.dtos.requests;

import lombok.Data;

@Data
public class CancelAuctionRequest {

    private String auctionId;
    private String sellerId;
}
