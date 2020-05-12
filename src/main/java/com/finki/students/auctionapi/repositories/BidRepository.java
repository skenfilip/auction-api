package com.finki.students.auctionapi.repositories;

import com.finki.students.auctionapi.domain.Bid;
import com.finki.students.auctionapi.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BidRepository extends JpaRepository<Bid, Long> {
    List<Bid> findByItem(Item item);
}
