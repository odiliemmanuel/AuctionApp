package org.auctionsystem.AuctionSystem.services;

import org.auctionsystem.AuctionSystem.data.models.*;
import org.auctionsystem.AuctionSystem.data.repositories.AuctionRepository;
import org.auctionsystem.AuctionSystem.data.repositories.BidderRepository;
import org.auctionsystem.AuctionSystem.data.repositories.UserRepository;
import org.auctionsystem.AuctionSystem.dtos.requests.NewBidderRequest;
import org.auctionsystem.AuctionSystem.dtos.responses.NewBidderResponse;
import org.auctionsystem.AuctionSystem.event.NewBidderEvent;
import org.auctionsystem.AuctionSystem.eventProducer.EventProducer;
import org.auctionsystem.AuctionSystem.exceptions.AuctionDoesNotExistException;
import org.auctionsystem.AuctionSystem.exceptions.InsufficientFundsException;
import org.auctionsystem.AuctionSystem.exceptions.Messages;
import org.auctionsystem.AuctionSystem.exceptions.UserDoesNotExistException;
import org.auctionsystem.AuctionSystem.utils.BidManagerMapper;
import org.springframework.stereotype.Service;


@Service
public class BidderManagementService {

    private final BidderRepository bidderRepository;
    private final AuctionRepository auctionRepository;
    private final UserRepository userRepository;
    private final EventProducer eventProducer;

    public BidderManagementService(BidderRepository bidderRepository,  AuctionRepository auctionRepository, UserRepository userRepository, EventProducer eventProducer) {
        this.bidderRepository = bidderRepository;
        this.auctionRepository = auctionRepository;
        this.userRepository = userRepository;
        this.eventProducer = eventProducer;
    }


    public NewBidderResponse bidAuction(NewBidderRequest bidderRequest) {
        User user = userRepository.findById(bidderRequest.getUserId()).get();
        Auction auction = auctionRepository.findById(bidderRequest.getAuctionId()).get();


        if(!auctionRepository.existsById(bidderRequest.getAuctionId()) || auction.getStatus() == AuctionStatus.CLOSED) {
            throw new AuctionDoesNotExistException(Messages.AUCTION_DOES_NOT_EXIST_EXCEPTION);
        }
        else if(!userRepository.existsById(bidderRequest.getUserId())) {
            throw new UserDoesNotExistException(Messages.USER_DOES_NOT_EXIST_EXCEPTION);
        }
        else if(auction.getProduct().getPrice() > Integer.parseInt(bidderRequest.getAmount())){
            throw new InsufficientFundsException(Messages.INSUFFICIENT_FUNDS_EXCEPTION);
        }

        else{
            Bid bidder = new Bid();

            bidder.setAuctionId(auction.getId());
            bidder.setUserId(user.getId());
            bidder.setAmount(Integer.parseInt(bidderRequest.getAmount()));

            if(auction.getCurrentHighestBid() > Integer.parseInt(bidderRequest.getAmount())){
                bidder.setBidStatus(BidStatus.OUTBID);
            }
            else{

                bidder.setBidStatus(BidStatus.WINNING);
            }

            bidderRepository.save(bidder);

            NewBidderEvent newBidderEvent = new NewBidderEvent(bidder.getId(), bidder.getAmount(), user.getEmailAddress(), bidder.getUserId());
            eventProducer.publishEvent(newBidderEvent);

            return BidManagerMapper.mapNewBidderResponseToBid(bidder);

        }



    }


}
