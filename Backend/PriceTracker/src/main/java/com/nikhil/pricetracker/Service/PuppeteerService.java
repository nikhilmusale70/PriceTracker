package com.nikhil.pricetracker.Service;
import com.nikhil.pricetracker.Models.Product;
import com.nikhil.pricetracker.Repository.ProductRepo;
import lombok.AllArgsConstructor;
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
    private String pageNavigateScript(String url){
        String command = chromeCommands.intialCommand();
        command += chromeCommands.chromeNavigateCommand(url);
        command += chromeCommands.endCommand();
        return command;
    }

    public String runPuppeteer() throws IOException, InterruptedException {
        List<Product> products = getAllProducts();
        for(Product product: products){
            String script = pageNavigateScript(product.getUrl());
            runPuppeteerScript(script);
            System.out.println("Currently on : " + product.getUrl());
        }
        return "updated the price";
    }
    private void runPuppeteerScript(String puppeteerScript) throws IOException, InterruptedException {
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
    }
}
