package com.emailsystem.emailer.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record NewUserEvent(
        String id,
        String type,
        String userId,
        String emailAddress,
        String firstName
) implements Event {

    public NewUserEvent() {
       this(null, null, null, null, null);
    }

    @Override
    public String getId() {
        return "";
    }

    @Override
    public String getType() {
        return "";
    }
}




