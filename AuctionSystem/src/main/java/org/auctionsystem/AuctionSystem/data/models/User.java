package org.auctionsystem.AuctionSystem.data.models;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class User {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String userName;
    private String emailAddress;
    private String phoneNumber;
    private String password;
    private UserRole userRole;
    private Wallet wallet;
}
