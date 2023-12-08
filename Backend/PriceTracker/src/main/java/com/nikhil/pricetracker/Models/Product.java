package com.nikhil.pricetracker.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Product{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_id")
    private Long productId;
    private String prdName;
    private String url;
    private String image;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_product_id", referencedColumnName = "product_id")
    private List<ProductPriceDetail> productPriceDetail;
}
