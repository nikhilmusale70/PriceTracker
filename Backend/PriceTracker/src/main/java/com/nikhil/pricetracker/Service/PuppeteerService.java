package com.nikhil.pricetracker.Service;
import com.nikhil.pricetracker.Models.Product;
import com.nikhil.pricetracker.Repository.ProductRepo;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

@Service
@AllArgsConstructor
public class PuppeteerService {
    ChromeCommands chromeCommands;
    private ProductRepo productRepo;


    private List<Product> getAllProducts(){
        return productRepo.findAll();
    }
    @SneakyThrows
    public String getProductName (String url){
        String command = chromeCommands.intialCommand();
        command += chromeCommands.chromeNavigateCommand(url);
        command += chromeCommands.getProductNameFromPage();
        command += chromeCommands.endCommand();
        String proName = runPuppeteerScript(command);
        return proName;
    }

    @SneakyThrows
    public int getProductPrice(String url) {
        String command = chromeCommands.intialCommand();
        command += chromeCommands.chromeNavigateCommand(url);
        command += chromeCommands.getProductPriceInString();
        command += chromeCommands.endCommand();
        String strPrice = runPuppeteerScript(command);
        return stringPriceToInt(strPrice);
    }
    public int stringPriceToInt(String strPrice){
        String numericString = strPrice.substring(1).trim();
        numericString = numericString.replaceAll(",", "");
        int priceInt = Integer.parseInt(numericString);
        return priceInt;
    }

    public String storePriceForAllProductsFromDb() throws IOException, InterruptedException {
        List<Product> products = getAllProducts();
        for(Product product: products){
            Long productId = product.getProductId();
            int productPrice = getProductPrice(product.getUrl());
            System.out.println(product.getPrdName() + " -> " + productPrice);

        }
        return "updated the price";
    }
    private String runPuppeteerScript(String puppeteerScript) throws IOException, InterruptedException {
        // Execute your Puppeteer script here using ProcessBuilder or any other method
        ProcessBuilder processBuilder = new ProcessBuilder("node", "-e", "\"" +puppeteerScript + "\"");
        processBuilder.redirectErrorStream(true);
        Process process = processBuilder.start();

        // Read the output from the Puppeteer script
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        StringBuilder puppeteerOutput = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            puppeteerOutput.append(line);
        }

        // Print the output from the Puppeteer script
        System.out.println("Puppeteer Output: " + puppeteerOutput);


        int exitCode = process.waitFor();
        System.out.println("Puppeteer script exited with code: " + exitCode);

        return puppeteerOutput.toString();
    }
}
