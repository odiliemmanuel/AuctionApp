package org.auctionsystem.AuctionSystem.utils;

import org.auctionsystem.AuctionSystem.data.models.Auction;
import org.auctionsystem.AuctionSystem.data.models.AuctionStatus;
import org.auctionsystem.AuctionSystem.data.models.Product;
import org.auctionsystem.AuctionSystem.dtos.requests.CreateAuctionRequest;
import org.auctionsystem.AuctionSystem.dtos.responses.CancelAuctionResponse;
import org.auctionsystem.AuctionSystem.dtos.responses.CreateAuctionResponse;
import org.auctionsystem.AuctionSystem.exceptions.InvalidInputException;
import org.auctionsystem.AuctionSystem.exceptions.LowPriceOfProductException;
import org.auctionsystem.AuctionSystem.exceptions.Messages;

import java.util.Optional;

public class AuctionManagerMapper {

    public static Auction mapCreateNewAuctionRequestToAuction(CreateAuctionRequest createAuctionRequest) {
        Auction auction = new Auction();
        Product product = new Product();


        if (!createAuctionRequest.getProduct().getName().matches("^[A-Za-z\\s]+$")) {
            throw new InvalidInputException(Messages.INVALID_INPUT_EXCEPTION);
        }
        product.setName(createAuctionRequest.getProduct().getName());

        if (!createAuctionRequest.getProduct().getDescription().matches("^[A-Za-z\\s]+$")) {
            throw new InvalidInputException(Messages.INVALID_INPUT_EXCEPTION);
        }
        product.setDescription(createAuctionRequest.getProduct().getDescription());

        double price = createAuctionRequest.getProduct().getPrice();
        if (price <= 0) {
            throw new InvalidInputException(Messages.INVALID_INPUT_EXCEPTION);
        }
        if (price < 10000) {
            throw new LowPriceOfProductException(Messages.LOW_PRICE_OF_PRODUCT_EXCEPTION);
        }
        product.setPrice(price);

        product.setImage(createAuctionRequest.getProduct().getImage());

        auction.setProduct(product);
        auction.setSellerId(createAuctionRequest.getSellerId());
        auction.setStartTime(createAuctionRequest.getStartTime());
        auction.setEndTime(createAuctionRequest.getEndTime());

        return auction;
    }

    public static CreateAuctionResponse mapCreateAuctionResponseToAuction(Auction auction) {
        CreateAuctionResponse createAuctionResponse = new CreateAuctionResponse();
        createAuctionResponse.setId(auction.getId());
        createAuctionResponse.setProduct(auction.getProduct());
        createAuctionResponse.setNumberOfBidders(0);
        createAuctionResponse.setStartTime(auction.getStartTime());
        createAuctionResponse.setEndTime(auction.getEndTime());
        createAuctionResponse.setCurrentHighestBid(auction.getCurrentHighestBid());
        createAuctionResponse.setStatus(AuctionStatus.OPEN);
        createAuctionResponse.setWinner(auction.getWinner());
        return createAuctionResponse;
    }

    public static CancelAuctionResponse mapCancelAuctionResponseToAuction(Optional<Auction> auction) {
        CancelAuctionResponse cancelAuctionResponse = new CancelAuctionResponse();
        cancelAuctionResponse.setStatus(AuctionStatus.CANCELLED);
        return cancelAuctionResponse;
    }
}