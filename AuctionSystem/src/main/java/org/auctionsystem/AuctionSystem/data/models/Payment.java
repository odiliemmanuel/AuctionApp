package org.auctionsystem.AuctionSystem.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class Payment {

    @Id
    private String id;
    private String amount;
    private PaymentStatus paymentStatus;
}
