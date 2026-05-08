package org.auctionsystem.AuctionSystem.event;

import java.time.LocalDateTime;

public class Event {

    private String id;
    private String type;
    private LocalDateTime timestamp;

    public Event(String id, String type){
        this.id = id;
        this.type = type;
        this.timestamp = LocalDateTime.now();
    }


    public String getType(){
        return this.type;
    }

    public String getId(){
        return this.id;
    }

    public LocalDateTime getTimestamp(){
        return this.timestamp;
    }
}
