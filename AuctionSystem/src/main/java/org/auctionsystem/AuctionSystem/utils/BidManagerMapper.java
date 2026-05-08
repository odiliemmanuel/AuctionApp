package org.auctionsystem.AuctionSystem.utils;

import org.auctionsystem.AuctionSystem.data.models.Bid;
import org.auctionsystem.AuctionSystem.dtos.responses.NewBidderResponse;

public class BidManagerMapper {

    public static NewBidderResponse mapNewBidderResponseToBid(Bid bid){
        NewBidderResponse newBidderResponse = new NewBidderResponse();

        newBidderResponse.setBidId(bid.getId());
        newBidderResponse.setAuctionId(bid.getAuctionId());
        newBidderResponse.setUserId(bid.getUserId());
        newBidderResponse.setBidStatus(bid.getBidStatus());
        newBidderResponse.setAmount(bid.getAmount());
        newBidderResponse.setTimeCreated(bid.getTimeCreated());

        return newBidderResponse;
    }
}
