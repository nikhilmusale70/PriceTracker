const puppeteer = require('puppeteer');

(async () => {
    // Launch a headless browser
    const browser = await puppeteer.launch({headless: false});

    // Open a new page
    const page = await browser.newPage();

    // Navigate to a URL
    await page.goto('https://www.facebook.com');

    // Take a screenshot
    await page.screenshot({ path: 'F:/Softwares/PriceTracker/PriceTracker/Backend/PriceTracker/src/main/resources/PuppeteerScripts/screenshot.png' });

    // Close the browser
    await browser.close();

    console.log('Screenshot taken and saved to screenshot.png');
})();
