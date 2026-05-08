package org.auctionsystem.AuctionSystem.data.repositories;

import org.auctionsystem.AuctionSystem.data.models.Payment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends MongoRepository<Payment, String> {
}
