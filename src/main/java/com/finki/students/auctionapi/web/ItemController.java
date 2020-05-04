package com.finki.students.auctionapi.web;

import com.finki.students.auctionapi.domain.Item;
import com.finki.students.auctionapi.domain.ItemRequest;
import com.finki.students.auctionapi.services.ItemService;
import com.finki.students.auctionapi.services.MapValidationErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @PostMapping("")
    public ResponseEntity<?> createNewItem(@Valid @RequestBody ItemRequest itemRequest, BindingResult result) throws Exception {

        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if (errorMap != null) {
            return errorMap;
        }

        Item item = itemService.saveOrUpdateItem(itemRequest);
        return new ResponseEntity<Item>(item, HttpStatus.CREATED);
    }

}
