package com.finki.students.auctionapi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BidRequest {

    @NotBlank(message = "Price is required")
    private String price;
    @NotBlank(message = "Item id is required")
    private String itemId;
}
