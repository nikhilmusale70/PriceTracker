package com.nikhil.pricetracker.Repository;

import com.nikhil.pricetracker.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
    Optional<Product> findByprdName(String name);
}
