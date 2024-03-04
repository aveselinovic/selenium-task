package org.luma.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 *
 * @author aveselinovic
 */
public class SignUpPage extends BasePage {

        @FindBy(id = "firstname")
        private WebElement firstNameField;

        @FindBy(id = "lastname")
        private WebElement lastNameField;

        @FindBy(id = "email_address")
        private WebElement emailField;

        @FindBy(id = "password")
        private WebElement passwordField;

        @FindBy(id = "password-confirmation")
        private WebElement confirmPasswordField;

        @FindBy(className = "submit")
        private WebElement createAnAccountBtn;

        public SignUpPage(WebDriver driver) {
            super(driver);
        }

        public void enterFirstName(String firstName) {
            waitForVisibility(firstNameField).sendKeys(firstName);
        }

        public void enterLastName(String lastName) {
            waitForVisibility(lastNameField).sendKeys(lastName);
        }

        public void enterEmail(String email) {
            waitForVisibility(emailField).sendKeys(email);
        }

        public void enterPassword(String password) {
            waitForVisibility(passwordField).sendKeys(password);
        }

        public void enterConfirmPassword(String confirmPassword) {
            waitForVisibility(confirmPasswordField).sendKeys(confirmPassword);
        }

        public void clickCreateAnAccountButton() {
            waitForClickable(createAnAccountBtn).click();
        }

        public void clearAllFields() {
            clearField(firstNameField);
            clearField(lastNameField);
            clearField(emailField);
            clearField(passwordField);
            clearField(confirmPasswordField);
        }

        public void signup(String firstName, String lastName, String email, String password) {
            enterFirstName(firstName);
            enterLastName(lastName);
            enterEmail(email);
            enterPassword(password);
            enterConfirmPassword(password);
            clickCreateAnAccountButton();
        }
    }