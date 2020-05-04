package com.finki.students.auctionapi.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "bid_name")
    private String bidName;
    @Column(name = "start_price")
    private Double startPrice;
    private String description;
    @Column(name = "item_no")
    private Integer itemNo;
    private String title;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date start;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date end;
    @ManyToOne
    @JoinColumn(name = "category_name")
    private Category category;
    @ManyToOne
    @JoinColumn(name = "seller_id")
    private User seller;

    @Column(name = "created_at")
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date createdAt;
    @Column(name = "updated_at")
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = new Date();
    }
}
