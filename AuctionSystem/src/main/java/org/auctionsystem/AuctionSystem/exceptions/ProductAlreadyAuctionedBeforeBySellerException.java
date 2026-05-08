package org.auctionsystem.AuctionSystem.exceptions;

public class ProductAlreadyAuctionedBeforeBySellerException extends RuntimeException {
    public ProductAlreadyAuctionedBeforeBySellerException(String message) {
        super(message);
    }
}
