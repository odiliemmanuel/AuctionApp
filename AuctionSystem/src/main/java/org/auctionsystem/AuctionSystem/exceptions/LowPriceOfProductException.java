package org.auctionsystem.AuctionSystem.exceptions;

public class LowPriceOfProductException extends RuntimeException {
    public LowPriceOfProductException(String message) {
        super(message);
    }
}
