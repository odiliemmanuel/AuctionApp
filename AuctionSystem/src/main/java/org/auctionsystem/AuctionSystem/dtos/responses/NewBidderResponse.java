package org.auctionsystem.AuctionSystem.dtos.responses;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.auctionsystem.AuctionSystem.data.models.BidStatus;

import java.time.LocalDateTime;


@Data
public class NewBidderResponse {

    private String bidId;
    private String userId;
    private String auctionId;
    private double amount;

    @JsonFormat(pattern = "yyyy/MM/dd HH:mm")
    private LocalDateTime timeCreated;

    private BidStatus bidStatus;
}
