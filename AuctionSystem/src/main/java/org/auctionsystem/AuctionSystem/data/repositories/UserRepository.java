package org.auctionsystem.AuctionSystem.data.repositories;

import org.auctionsystem.AuctionSystem.data.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    User findByEmailAddress(String emailAddress);

    boolean existsByEmailAddress(String emailAddress);
}
