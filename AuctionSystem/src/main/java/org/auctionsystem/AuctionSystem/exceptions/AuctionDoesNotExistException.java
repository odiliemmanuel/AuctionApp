package org.auctionsystem.AuctionSystem.exceptions;

public class AuctionDoesNotExistException extends RuntimeException {
    public AuctionDoesNotExistException(String message) {
        super(message);
    }
}
