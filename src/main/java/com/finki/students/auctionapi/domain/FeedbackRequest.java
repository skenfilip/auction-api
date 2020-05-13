package com.finki.students.auctionapi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeedbackRequest {
    private int positiveFeedbacks;
    private int negativeFeedbacks;
}
