package org.auctionsystem.AuctionSystem.data.models;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class Wallet {

    private double balance;

}
