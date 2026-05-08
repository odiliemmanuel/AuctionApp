package org.auctionsystem.AuctionSystem.dtos.responses;

import lombok.Data;

@Data
public class UserSignUpResponse {

    String username;
    String phoneNumber;
    String message;
}
