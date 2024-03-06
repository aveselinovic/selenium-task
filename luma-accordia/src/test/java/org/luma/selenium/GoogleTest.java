package org.luma.selenium;

import org.testng.Assert;
import org.testng.annotations.Test;

public class GoogleTest extends BaseTest {

    @Test
    public void testGooglePageTitle() {
        // Open Google homepage
        driver.get("https://www.google.com");

        // Verify page title
        String pageTitle = driver.getTitle();
        System.out.println("Page title: " + pageTitle);

        // Assert that the page title contains "Google"
        Assert.assertTrue(pageTitle.contains("Random"), "Page title doesn't contain 'Google'");
    }
}
