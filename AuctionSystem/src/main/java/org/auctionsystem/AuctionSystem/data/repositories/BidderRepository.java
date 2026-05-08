package org.auctionsystem.AuctionSystem.data.repositories;

import org.auctionsystem.AuctionSystem.data.models.Bid;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BidderRepository extends MongoRepository<Bid, String> {
}
