package org.auctionsystem.AuctionSystem.services;

import org.auctionsystem.AuctionSystem.data.models.Auction;
import org.auctionsystem.AuctionSystem.data.models.User;
import org.auctionsystem.AuctionSystem.data.repositories.AuctionRepository;
import org.auctionsystem.AuctionSystem.data.repositories.ProductRepository;
import org.auctionsystem.AuctionSystem.data.repositories.UserRepository;
import org.auctionsystem.AuctionSystem.dtos.requests.CancelAuctionRequest;
import org.auctionsystem.AuctionSystem.dtos.requests.CreateAuctionRequest;
import org.auctionsystem.AuctionSystem.dtos.responses.CancelAuctionResponse;
import org.auctionsystem.AuctionSystem.dtos.responses.CreateAuctionResponse;
import org.auctionsystem.AuctionSystem.event.AuctionCancelledEvent;
import org.auctionsystem.AuctionSystem.event.NewAuctionEvent;
import org.auctionsystem.AuctionSystem.eventProducer.EventProducer;
import org.auctionsystem.AuctionSystem.exceptions.AuctionDoesNotExistException;
import org.auctionsystem.AuctionSystem.exceptions.Messages;
import org.auctionsystem.AuctionSystem.exceptions.ProductAlreadyAuctionedBeforeBySellerException;
import org.auctionsystem.AuctionSystem.exceptions.UserDoesNotExistException;
import org.auctionsystem.AuctionSystem.utils.AuctionManagerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuctionManagementService {

    @Autowired
    private AuctionRepository auctionRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventProducer eventProducer;

    public CreateAuctionResponse organizeNewAuction(CreateAuctionRequest createAuctionRequest) {
        Auction auction = AuctionManagerMapper.mapCreateNewAuctionRequestToAuction(createAuctionRequest);
        Optional<User> user = userRepository.findById(auction.getSellerId());

        if(!user.isPresent()) {
            throw new UserDoesNotExistException(Messages.USER_DOES_NOT_EXIST_EXCEPTION);
        }
        boolean alreadyExists = auctionRepository.findAll()
                .stream()
                .anyMatch(existing ->
                        existing.getSellerId().equals(auction.getSellerId()) &&
                                existing.getProduct().getName()
                                        .equalsIgnoreCase(auction.getProduct().getName())
                );

        if (alreadyExists) {
            throw new ProductAlreadyAuctionedBeforeBySellerException(
                    Messages.PRODUCT_ALREADY_AUCTIONED_BEFORE_BY_SELLER_EXCEPTION
            );
        }

        productRepository.save(auction.getProduct());

        auctionRepository.save(auction);

        NewAuctionEvent newAuctionEvent = new NewAuctionEvent(auction.getId(), user.get().getEmailAddress());
        eventProducer.publishEvent(newAuctionEvent);

        return AuctionManagerMapper.mapCreateAuctionResponseToAuction(auction);
    }

    public CancelAuctionResponse cancelAuction(CancelAuctionRequest cancelAuctionRequest) {
        Optional<Auction> auction = auctionRepository.findById(cancelAuctionRequest.getAuctionId());
        Optional<User> user = userRepository.findById(cancelAuctionRequest.getSellerId());

        if (!auction.isPresent()) {
            throw new AuctionDoesNotExistException(Messages.AUCTION_DOES_NOT_EXIST_EXCEPTION);
        }

        auctionRepository.delete(auction.get());

        AuctionCancelledEvent auctionCancelledEvent = new AuctionCancelledEvent(auction.get().getId(),  user.get().getEmailAddress());
        eventProducer.publishEvent(auctionCancelledEvent);

        return AuctionManagerMapper.mapCancelAuctionResponseToAuction(auction);
    }
}