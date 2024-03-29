package org.luma.selenium;

import org.luma.util.WebDriverProvider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class BaseTest {
    protected WebDriver driver;
    private final WebDriverProvider webDriverProvider = new WebDriverProvider();

    @BeforeMethod
    public void setUp() {
        // Set path to ChromeDriver executable
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver/chromedriver");

        try {
            // Initialize ChromeDriver with options
            driver = webDriverProvider.get();
            if (driver == null) {
                driver = new ChromeDriver(getChromeOptions());
                webDriverProvider.set(driver);
            }
        } catch (Exception e) {
            System.out.println("An error occurred while setting up the WebDriver: " + e.getMessage());
        }
    }

    @AfterMethod
    public void tearDown() {
        // Quit WebDriver instance
        try {
            if (driver != null) {
                driver.quit();
                webDriverProvider.get().quit(); // Quit WebDriver from thread-local storage
                webDriverProvider.clear(); // Clear thread-local storage
            }
        } catch (Exception e) {
            System.out.println("An error occurred while tearing down the WebDriver: " + e.getMessage());
        }
    }

    private ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();

        // Check if headless_mode environment variable is set
        String headlessMode = System.getenv("headless_mode");
        if (headlessMode != null && !headlessMode.isEmpty()) {
            // Use value of headless_mode environment variable
            boolean isHeadless = Boolean.parseBoolean(headlessMode);
            if (isHeadless) {
                options.addArguments("--headless");
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
            }
        } else {
            // Read headless mode configuration from local properties file
            boolean isHeadless = Boolean.parseBoolean(getPropertyValue("headless.mode", "false"));
            if (isHeadless) {
                options.addArguments("--headless");
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
            }
        }

        return options;
    }

    private String getPropertyValue(String propertyName, String defaultValue) {
        Properties properties = new Properties();
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            if (inputStream != null) {
                properties.load(inputStream);
                return properties.getProperty(propertyName, defaultValue);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return defaultValue;
    }
}
