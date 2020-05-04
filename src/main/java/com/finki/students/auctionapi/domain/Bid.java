package com.finki.students.auctionapi.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Bid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double price;
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date time;
    @JsonFormat
    @ManyToOne
    @JoinColumn(name = "buyer_id")
    private User buyer;
    @JsonFormat
    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @PrePersist
    protected void onCreate() {
        this.time = new Date();
    }
}
