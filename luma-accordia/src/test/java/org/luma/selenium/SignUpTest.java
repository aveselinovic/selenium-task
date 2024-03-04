package org.luma.selenium;

import org.luma.selenium.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.luma.selenium.pages.SignUpPage;
import org.luma.selenium.pages.UserAccountPage;
import org.luma.selenium.pages.HomePage;
import org.luma.selenium.models.User;
import io.qameta.allure.*;


public class SignUpTest extends BaseTest {
    HomePage homePage;
    SignUpPage signupPage;
    UserAccountPage userAccountPage;

    @DataProvider(name = "userData")
    public Object[][] userData() {
        return new Object[][] {
                {User.generateRandomFirstName(), User.generateRandomLastName(),
                        User.generateRandomEmail(), User.getPassword()}
        };
    }

    @Test(dataProvider = "userData")
    @Description("Check if the new user successfully register an account")
    public void testRegisterNewUser(String firstName, String lastName, String email, String password) {
        homePage = new HomePage(driver);
        signupPage = new SignUpPage(driver);
        userAccountPage = new UserAccountPage(driver);

        homePage.openHomePage();
        homePage.clickCreateAccountButton();
        signupPage.signup(firstName, lastName, email, password);
        Assert.assertTrue(userAccountPage.isSuccessRegistrationMsgDisplayed(), "Success registration message is absent");
    }
}
