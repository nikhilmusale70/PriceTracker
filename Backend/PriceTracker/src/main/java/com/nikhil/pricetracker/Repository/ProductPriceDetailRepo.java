package com.nikhil.pricetracker.Repository;

import com.nikhil.pricetracker.Models.ProductPriceDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductPriceDetailRepo extends JpaRepository<ProductPriceDetail, Long> {

}
