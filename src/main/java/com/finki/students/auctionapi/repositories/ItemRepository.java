package com.finki.students.auctionapi.repositories;

import com.finki.students.auctionapi.domain.Category;
import com.finki.students.auctionapi.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    @Override
    List<Item> findAll();
    List<Item> findAllByCategory(Category category);
}
