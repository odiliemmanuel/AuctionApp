package org.auctionsystem.AuctionSystem.event;

public class NewUserEvent extends Event{


    private String userId;
    private String emailAddress;
    private String firstName;

    public NewUserEvent(String userId, String emailAddress, String firstName) {
        super("EVT-" + userId, "NEW_USER");
        this.userId = userId;
        this.emailAddress = emailAddress;
        this.firstName = firstName;
    }

    public String getUserId() {
        return userId;
    }
    public String getEmailAddress() {
        return emailAddress;
    }
    public String getFirstName() {
        return firstName;
    }
}
