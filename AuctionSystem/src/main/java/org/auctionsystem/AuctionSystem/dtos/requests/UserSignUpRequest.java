package org.auctionsystem.AuctionSystem.dtos.requests;

import lombok.Data;

@Data
public class UserSignUpRequest {

    String firstName;
    String lastName;
    String userName;
    String emailAddress;
    String phoneNumber;
    String password;
}
