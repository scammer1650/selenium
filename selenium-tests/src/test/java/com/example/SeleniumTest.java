package com.example.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SeleniumTest {

    private WebDriver driver;
    private String testUrl = System.getProperty("testUrl", "https://youtube.com");
    private String browser = System.getProperty("browser", "chrome");

    @BeforeMethod
public void setup() {
    testUrl = System.getProperty("testUrl", "").trim();
    if (testUrl.isEmpty()) {
        testUrl = "https://chatgpt.com"; // fallback value
    }
    browser = System.getProperty("browser", "chrome").trim();
    if (browser.isEmpty()) {
        browser = "chrome"; // fallback value
    }
    // Optionally ensure the URL has a protocol
    if (!testUrl.toLowerCase().startsWith("http://") && !testUrl.toLowerCase().startsWith("https://")) {
        testUrl = "https://" + testUrl;
    }
    switch (browser.toLowerCase()) {
        case "firefox":
            driver = new FirefoxDriver();
            break;
        case "edge":
            driver = new EdgeDriver();
            break;
        case "chrome":
        default:
            driver = new ChromeDriver();
            break;
    }
}


    @Test
    public void testWebsiteTitle() {
        driver.get(testUrl);
        // A simple assertion to check if the title is not empty
        Assert.assertFalse(driver.getTitle().isEmpty(), "Page title should not be empty");
    }

    @AfterMethod
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
