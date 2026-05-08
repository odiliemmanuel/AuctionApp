package com.emailsystem.emailer.model;

public record EmailEvent(

        String to,
        String subject,
        String body

) {}
