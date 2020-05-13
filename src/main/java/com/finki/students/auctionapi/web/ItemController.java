package com.finki.students.auctionapi.web;

import com.finki.students.auctionapi.domain.Item;
import com.finki.students.auctionapi.domain.ItemRequest;
import com.finki.students.auctionapi.services.ItemService;
import com.finki.students.auctionapi.services.MapValidationErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @PostMapping("")
    public ResponseEntity<?> createNewItem(@Valid @RequestBody ItemRequest itemRequest, BindingResult result, Principal principal) throws Exception {

        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if (errorMap != null) {
            return errorMap;
        }

        Item item = itemService.saveOrUpdateItem(itemRequest, principal.getName());
        return new ResponseEntity<Item>(item, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllItems() throws Exception {

        List<Item> items = itemService.getAllItems();
        return new ResponseEntity<List<Item>>(items, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<?> getAllItems(@RequestParam(name = "category") String category) throws Exception {

        List<Item> items = itemService.getCategoryItems(category);
        return new ResponseEntity<List<Item>>(items, HttpStatus.OK);
    }

}
