package com.nikhil.pricetracker.Service;

import org.springframework.stereotype.Service;

@Service
public class ChromeCommands {
    boolean headlessFlag = false;
    public String intialCommand(){
        String command = "const puppeteer = require('puppeteer'); (async () => { const browser = await puppeteer.launch({ headless: " + headlessFlag + " }); const page = await browser.newPage();";
        return command;
    }
    public String endCommand(){
        String command = " await browser.close(); })();";
        return command;
    }
    public String chromeNavigateCommand(String url){
        String command = " await page.goto('" + url + "'); const extractedData = await page.evaluate(() => { const element = document.getElementsByClassName('B_NuCI')[0]; return element ? element.textContent : null;}); console.log( 'Extracted Data:', extractedData);";
        return command;
    }
}
