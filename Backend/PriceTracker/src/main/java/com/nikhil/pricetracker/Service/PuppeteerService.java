package com.nikhil.pricetracker.Service;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@AllArgsConstructor
public class PuppeteerService {
    public void runPuppeteerScript() throws IOException, InterruptedException {
        // Execute your Puppeteer script here using ProcessBuilder or any other method
        // For example, you can use the node4j library to run a Node.js script from Java
        ProcessBuilder processBuilder = new ProcessBuilder("node", "C:/Users/nmusale/IdeaProjects/PriceTracker/src/main/resources/PuppeteerScripts/script1.js");
        Process process = processBuilder.start();
        int exitCode = process.waitFor();
        System.out.println("Puppeteer script exited with code: " + exitCode);
    }
}
