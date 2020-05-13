package com.finki.students.auctionapi.repositories;

import com.finki.students.auctionapi.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, String> {
}
