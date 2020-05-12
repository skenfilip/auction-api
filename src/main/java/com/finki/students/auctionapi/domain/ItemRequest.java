package com.finki.students.auctionapi.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemRequest {

    @NotBlank(message = "Bid name is required")
    private String bidName;
    @NotBlank(message = "Start price is required")
    private String startPrice;
    @NotBlank(message = "End date is required")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String end;
    @NotBlank(message = "Category name date is required")
    private String categoryName;
}
