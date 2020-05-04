package com.finki.students.auctionapi.web;

import com.finki.students.auctionapi.domain.Bid;
import com.finki.students.auctionapi.domain.BidRequest;
import com.finki.students.auctionapi.domain.Category;
import com.finki.students.auctionapi.services.BidService;
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
import java.security.Principal;

@RestController
@RequestMapping("/api/bid")
public class BidController {

    @Autowired
    private BidService bidService;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @PostMapping("")
    public ResponseEntity<?> createNewBid(@Valid @RequestBody BidRequest bidRequest, BindingResult result, Principal principal) throws Exception {

        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if (errorMap != null) {
            return errorMap;
        }

        Bid bid = bidService.saveBid(bidRequest, principal.getName());
        return new ResponseEntity<Bid>(bid, HttpStatus.CREATED);
    }
}
