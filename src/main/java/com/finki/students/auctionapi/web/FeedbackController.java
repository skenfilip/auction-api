package com.finki.students.auctionapi.web;

import com.finki.students.auctionapi.domain.FeedbackRequest;
import com.finki.students.auctionapi.domain.User;
import com.finki.students.auctionapi.services.FeedbackService;
import com.finki.students.auctionapi.services.MapValidationErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
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

    @GetMapping("/user_id/{userId}")
    public ResponseEntity<?> getFeedbacks(
            @PathVariable("userId") Long userId) throws Exception{
        Integer positiveFeedback = feedbackService.getPositiveFeedback(userId);
        Integer negativeFeedback = feedbackService.getNegativeFeedback(userId);
        FeedbackRequest feedbackRequest = new FeedbackRequest(positiveFeedback, negativeFeedback);
        return new ResponseEntity<FeedbackRequest>(feedbackRequest, HttpStatus.OK);
    }
}
