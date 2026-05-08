package org.auctionsystem.AuctionSystem.data.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
@Data
public class Buyer extends User{

    private double walletBalance;

}
