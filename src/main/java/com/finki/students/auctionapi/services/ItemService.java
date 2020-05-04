package com.finki.students.auctionapi.services;

import com.finki.students.auctionapi.domain.Category;
import com.finki.students.auctionapi.domain.Item;
import com.finki.students.auctionapi.domain.ItemRequest;
import com.finki.students.auctionapi.domain.User;
import com.finki.students.auctionapi.repositories.CategoryRepository;
import com.finki.students.auctionapi.repositories.ItemRepository;
import com.finki.students.auctionapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public Item saveOrUpdateItem(ItemRequest itemRequest) throws Exception {
        User seller = userRepository.findById(Long.parseLong(itemRequest.getSellerId())).orElseThrow(() -> new Exception());
        Category category = categoryRepository.findById(itemRequest.getCategoryName()).orElseThrow(() -> new Exception());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Item item = new Item(
                0L,
                itemRequest.getBidName(),
                Double.parseDouble(itemRequest.getStartPrice()),
                itemRequest.getDescription(),
                Integer.parseInt(itemRequest.getItemNo()),
                itemRequest.getTitle(),
                simpleDateFormat.parse(itemRequest.getStart()),
                simpleDateFormat.parse(itemRequest.getEnd()),
                category,
                seller,
                new Date(),
                null);
        return itemRepository.save(item);
    }
}
