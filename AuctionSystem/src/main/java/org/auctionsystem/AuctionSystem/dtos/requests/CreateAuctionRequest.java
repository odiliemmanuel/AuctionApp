package org.auctionsystem.AuctionSystem.dtos.requests;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.auctionsystem.AuctionSystem.data.models.Product;
import java.time.LocalDateTime;


@Data
public class CreateAuctionRequest {

    private Product product;
    private String sellerId;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime startTime = LocalDateTime.now();

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime endTime;

}