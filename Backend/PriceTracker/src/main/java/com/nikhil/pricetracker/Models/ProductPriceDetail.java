package com.nikhil.pricetracker.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Entity
@AllArgsConstructor
@Getter
@NoArgsConstructor
@Builder
public class ProductPriceDetail{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="productPrice_id")
    private Long id;
    private Long price;
    private LocalDate date;
}
