package org.luma.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ExampleTest extends BaseTest {

    @Test
    public void testAnotherPageTitle() {
        // Open another website
        driver.get("https://www.example.com");

        // Verify page title
        String pageTitle = driver.getTitle();
        System.out.println("Page title: " + pageTitle);

        // Assert that the page title contains "Example"
        Assert.assertTrue(pageTitle.contains("Esxample Domain"), "Page title doesn't contain 'Example'");
    }
}