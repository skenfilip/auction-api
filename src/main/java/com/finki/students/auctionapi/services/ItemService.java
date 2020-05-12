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
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public Item saveOrUpdateItem(ItemRequest itemRequest, String username) throws Exception {
        User seller = userRepository.findByUsername(username);
        Category category = categoryRepository.findById(itemRequest.getCategoryName()).orElseThrow(() -> new Exception());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Item item = new Item(
                0L,
                itemRequest.getBidName(),
                Double.parseDouble(itemRequest.getStartPrice()),
                simpleDateFormat.parse(itemRequest.getEnd()),
                category,
                seller,
                new Date(),
                null);
        return itemRepository.save(item);
    }

    public List<Item> getAllItems() {
        List<Item> items = itemRepository.findAll();
        return items.stream().filter((item -> item.getEnd().after(new Date()))).collect(Collectors.toList());
    }
}
