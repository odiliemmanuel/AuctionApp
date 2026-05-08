package org.auctionsystem.AuctionSystem.dtos.responses;

import lombok.Data;

@Data
public class UserLoginResponse {

    String username;
    String firstName;
    String phoneNumber;
    String message;
}
