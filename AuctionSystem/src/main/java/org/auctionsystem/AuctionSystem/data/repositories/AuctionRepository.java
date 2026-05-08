package org.auctionsystem.AuctionSystem.data.repositories;

import org.auctionsystem.AuctionSystem.data.models.Auction;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuctionRepository extends MongoRepository<Auction, String> {
}
