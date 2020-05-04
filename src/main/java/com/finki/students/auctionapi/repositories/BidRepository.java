package com.finki.students.auctionapi.repositories;

import com.finki.students.auctionapi.domain.Bid;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BidRepository extends JpaRepository<Bid, Long> {
}
