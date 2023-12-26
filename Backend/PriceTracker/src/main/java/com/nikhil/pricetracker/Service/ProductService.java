package com.nikhil.pricetracker.Service;

import com.nikhil.pricetracker.Dto.ProductDto;
import com.nikhil.pricetracker.Models.Product;
import com.nikhil.pricetracker.Models.ProductPriceDetail;
import com.nikhil.pricetracker.Models.User;
import com.nikhil.pricetracker.Repository.ProductRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService {
    private ProductRepo productRepo;
    private PuppeteerService puppeteerService;
    private UserService userService;
    public Product getProductOrSave(ProductDto productDto) {
        //get product's id, if not present in db save
        String url = productDto.getUrl();
        Long userId = productDto.getUserId();
        User user = userService.getUserById(userId);

        String productName = getProductNameFromUrl(url);

        Product product = productRepo.findByprdName(productName).orElse(null);
        if(product==null){
            List<ProductPriceDetail> prdPriceList = new ArrayList<>();
            product = Product.builder().prdName(productName).url(url).image("this is a image").users(new ArrayList<>()).productPriceDetail(prdPriceList).build();
        }

        //if product is not null, check if today's date is present or not
        LocalDate lastDate = null;
        if(!product.getProductPriceDetail().isEmpty()) {
            lastDate = product.getProductPriceDetail().get(product.getProductPriceDetail().size() - 1).getDate();
        }
        if(lastDate==null || !lastDate.equals(LocalDate.now())){
            int productPrice = getProductPriceFromUrl(url);
            ProductPriceDetail prd = ProductPriceDetail.builder().price((long) productPrice).date(LocalDate.now()).build();
            product.getProductPriceDetail().add(prd);
        }

        if(!product.getUsers().contains(user)){
            product.getUsers().add(user);
        }
        productRepo.save(product);
        return product;
    }

    private int getProductPriceFromUrl(String url){
        return puppeteerService.getProductPrice(url);
    }

    private String getProductNameFromUrl(String url){
        //do some magic and retrieve the name
        return puppeteerService.getProductName(url);
    }

}
