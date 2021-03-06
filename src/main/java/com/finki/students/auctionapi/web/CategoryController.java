package com.finki.students.auctionapi.web;

import com.finki.students.auctionapi.domain.Category;
import com.finki.students.auctionapi.domain.Item;
import com.finki.students.auctionapi.domain.ItemRequest;
import com.finki.students.auctionapi.services.CategoryService;
import com.finki.students.auctionapi.services.MapValidationErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @PostMapping("")
    public ResponseEntity<?> createNewCategory(@Valid @RequestBody Category category, BindingResult result) throws Exception {

        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if (errorMap != null) {
            return errorMap;
        }

        Category category1 = categoryService.saveCategory(category);
        return new ResponseEntity<Category>(category1, HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<?> getCategories() {

        List<Category> categories = categoryService.getCategories();
        return new ResponseEntity<List<Category>>(categories, HttpStatus.OK);
    }
}
