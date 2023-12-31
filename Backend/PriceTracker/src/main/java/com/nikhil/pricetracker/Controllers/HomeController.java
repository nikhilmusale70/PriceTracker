package com.nikhil.pricetracker.Controllers;

import com.nikhil.pricetracker.Dto.ProductDto;
import com.nikhil.pricetracker.Models.Product;
import com.nikhil.pricetracker.Service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/home")
@AllArgsConstructor
public class HomeController {
    ProductService productService;

    @PostMapping("/search")
    public Product getProduct(@RequestBody ProductDto productDto){
        return productService.getProductOrSave(productDto);
    }
}
