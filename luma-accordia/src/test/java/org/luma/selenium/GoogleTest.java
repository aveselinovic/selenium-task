package org.luma.selenium;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class GoogleTest {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        // Set path to ChromeDriver executable
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver/chromedriver");

        // Initialize ChromeDriver
        driver = new ChromeDriver();
    }

    @Test
    public void testGooglePageTitle() {
        // Open Google homepage
        driver.get("https://www.google.com");

        // Verify page title
        String pageTitle = driver.getTitle();
        System.out.println("Page title: " + pageTitle);

        // Assert that the page title contains "Google"
        Assert.assertTrue(pageTitle.contains("Google"), "Page title doesn't contain 'Google'");
    }

    @AfterMethod
    public void tearDown() {
        // Quit WebDriver instance
        driver.quit();
    }
}
