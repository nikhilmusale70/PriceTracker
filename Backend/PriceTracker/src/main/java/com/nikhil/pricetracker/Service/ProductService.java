package com.nikhil.pricetracker.Service;

import com.nikhil.pricetracker.Models.Product;
import com.nikhil.pricetracker.Models.ProductPriceDetail;
import com.nikhil.pricetracker.Repository.ProductRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService {
    private ProductRepo productRepo;
    public Product getProduct(String url){
        //get product's id, if not present in db save
        String productName = getProductNameFromUrl(url);
        Optional<Product> product = productRepo.findByprdName(productName);
        if(product.isEmpty()){
            List<ProductPriceDetail> prdPriceList = new ArrayList<>();
            ProductPriceDetail prd1 = ProductPriceDetail.builder().productId(2L).price(100L).date(new Date()).build();
            prdPriceList.add(prd1);
            Product prd = Product.builder().prdName(productName).url(url).image("this is a image").productPriceDetail(prdPriceList).build();
            return productRepo.save(prd);
        }
        return product.orElseThrow();
    }

    private String getProductNameFromUrl(String url){
        //do some magic and retrieve the name
        return "trimmer";
    }

}
