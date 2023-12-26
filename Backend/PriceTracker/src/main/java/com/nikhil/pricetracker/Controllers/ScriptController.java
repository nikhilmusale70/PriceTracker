package com.nikhil.pricetracker.Controllers;

import com.nikhil.pricetracker.Service.PuppeteerService;
import lombok.AllArgsConstructor;
import org.hibernate.query.criteria.JpaCriteriaUpdate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@RestController
public class ScriptController {
    PuppeteerService puppeteerService;

    @GetMapping("/runScript")
    public String runScript() {
        try {
            puppeteerService.storePriceForAllProductsFromDb();
            return "Puppeteer script executed successfully.";
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return "Error executing Puppeteer script.";
        }
    }
}

