package org.luma.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 *
 * @author aveselinovic
 */
public class UserAccountPage extends BasePage {
    @FindBy(className = "success")
    private WebElement successRegistrationMsg;

    public UserAccountPage(WebDriver driver) {
        super(driver);
    }

    public boolean isSuccessRegistrationMsgDisplayed() {
        return waitForVisibility(successRegistrationMsg) != null;
    }
}
