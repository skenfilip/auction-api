package com.finki.students.auctionapi.services;

import com.finki.students.auctionapi.domain.Item;
import com.finki.students.auctionapi.domain.User;
import com.finki.students.auctionapi.repositories.ItemRepository;
import com.finki.students.auctionapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.function.EntityResponse;

@Service
public class FeedbackService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private UserRepository userRepository;

    public User savePositiveFeedback(Long userId, Long itemId) throws Exception {
        Item item = itemRepository.findById(itemId).orElseThrow(() -> new Exception());
        User user = userRepository.findById(userId).orElseThrow(() -> new Exception());

        user.getPositiveItems().add(item);
        return userRepository.save(user);
    }

    public User saveNegativeFeedback(Long userId, Long itemId) throws Exception {
        Item item = itemRepository.findById(itemId).orElseThrow(() -> new Exception());
        User user = userRepository.findById(userId).orElseThrow(() -> new Exception());

        user.getNegativeItems().add(item);
        return userRepository.save(user);
    }

    public int getPositiveFeedback(Long userId) throws Exception{
        User user = userRepository.findById(userId).orElseThrow(() -> new Exception());
        return user.getPositiveItems().size();
    }

    public int getNegativeFeedback(Long userId) throws Exception{
        User user = userRepository.findById(userId).orElseThrow(() -> new Exception());
        return user.getNegativeItems().size();
    }
}
