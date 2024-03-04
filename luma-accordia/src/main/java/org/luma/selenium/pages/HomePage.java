package org.luma.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.luma.selenium.constants.Constants.HOMEPAGE;

public class HomePage extends BasePage {

    @FindBy(linkText = "Sign In")
    private WebElement signInBtn;

    @FindBy(linkText = "Create an Account")
    private WebElement createAnAccountBtn;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void openHomePage() {
        driver.get(HOMEPAGE);
    }

    public void clickCreateAccountButton() {
        waitForClickable(createAnAccountBtn).click();

    }

}

