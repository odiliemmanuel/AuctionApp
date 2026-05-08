package org.auctionsystem.AuctionSystem.data.repositories;

import org.auctionsystem.AuctionSystem.data.models.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
}
