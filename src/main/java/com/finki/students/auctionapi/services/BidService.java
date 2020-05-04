package com.finki.students.auctionapi.services;

import com.finki.students.auctionapi.domain.Bid;
import com.finki.students.auctionapi.domain.BidRequest;
import com.finki.students.auctionapi.domain.Item;
import com.finki.students.auctionapi.domain.User;
import com.finki.students.auctionapi.repositories.BidRepository;
import com.finki.students.auctionapi.repositories.ItemRepository;
import com.finki.students.auctionapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class BidService {

    @Autowired
    private BidRepository bidRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ItemRepository itemRepository;

    public Bid saveBid(BidRequest bidRequest, String username) throws Exception {
        User user = userRepository.findByUsername(username);
        Item item = itemRepository.findById(Long.parseLong(bidRequest.getItemId())).orElseThrow(() -> new Exception());
        Bid bid = new Bid(
                0L,
                Double.parseDouble(bidRequest.getPrice()),
                new Date(),
                user,
                item);
        return bidRepository.save(bid);
    }
}
