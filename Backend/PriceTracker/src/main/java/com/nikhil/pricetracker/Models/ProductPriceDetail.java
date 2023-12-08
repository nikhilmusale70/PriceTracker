package com.nikhil.pricetracker.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@AllArgsConstructor
@Getter
@NoArgsConstructor
@Builder
public class ProductPriceDetail{
    @Id
    @Column(name="product_id")
    private Long productId;
    private Long price;
    private Date date;
}
