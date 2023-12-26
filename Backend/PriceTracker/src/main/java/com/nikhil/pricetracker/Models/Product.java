package com.nikhil.pricetracker.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder

public class Product{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productId;
    private String prdName;
    @Column(length = 2000)
    private String url;
    private String image;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "user_products", joinColumns = {
            @JoinColumn(name = "product_id", referencedColumnName = "productId")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "user_id", referencedColumnName = "id")
            })
    @JsonIgnore
    private List<User> users;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "product_Id", referencedColumnName = "productId")
    private List<ProductPriceDetail> productPriceDetail;
}
