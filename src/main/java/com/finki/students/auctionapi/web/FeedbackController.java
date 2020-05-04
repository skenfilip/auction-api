package com.finki.students.auctionapi.web;

import com.finki.students.auctionapi.domain.User;
import com.finki.students.auctionapi.services.FeedbackService;
import com.finki.students.auctionapi.services.MapValidationErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @PostMapping("/user_id/{userId}/item_id/{itemId}/outcome/{outcome}")
    public ResponseEntity<?> createNewFeedback(
            @PathVariable("userId") Long userId,
            @PathVariable("itemId") Long itemId,
            @PathVariable("outcome") String outcome) throws Exception {

        User user = null;
        if (outcome.equals("POSITIVE"))
            user = feedbackService.savePositiveFeedback(userId, itemId);
        else if (outcome.equals("NEGATIVE"))
            user = feedbackService.saveNegativeFeedback(userId, itemId);
        return new ResponseEntity<User>(user, HttpStatus.CREATED);
    }
}
