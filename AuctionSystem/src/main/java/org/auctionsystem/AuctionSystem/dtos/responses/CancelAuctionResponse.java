package org.auctionsystem.AuctionSystem.dtos.responses;

import lombok.Data;
import org.auctionsystem.AuctionSystem.data.models.AuctionStatus;

@Data
public class CancelAuctionResponse {

    private AuctionStatus status;
}
