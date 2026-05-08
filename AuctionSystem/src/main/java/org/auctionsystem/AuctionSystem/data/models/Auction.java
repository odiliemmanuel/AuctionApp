package org.auctionsystem.AuctionSystem.data.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Document
@Data
public class Auction {

    @Id
    private String id;
    private Product product;
    private String sellerId;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime startTime;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime endTime;

    private AuctionStatus status = AuctionStatus.OPEN;
    private List<Bid> bidders;
    private User winner;
    //    private int numberOfBidders;
    private double currentHighestBid;

}
