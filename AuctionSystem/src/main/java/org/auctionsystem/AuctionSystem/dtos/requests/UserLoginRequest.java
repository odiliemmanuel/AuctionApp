package org.auctionsystem.AuctionSystem.dtos.requests;

import lombok.Data;

@Data
public class UserLoginRequest {

    String emailAddress;
    String password;
}
