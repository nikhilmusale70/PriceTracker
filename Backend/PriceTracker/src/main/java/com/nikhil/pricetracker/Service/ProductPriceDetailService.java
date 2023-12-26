package com.nikhil.pricetracker.Service;

import com.nikhil.pricetracker.Repository.ProductPriceDetailRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductPriceDetailService {
    private ProductPriceDetailRepo productPriceDetailRepo;

}
