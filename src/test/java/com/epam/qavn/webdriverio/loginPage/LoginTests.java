package com.epam.qavn.webdriverio.loginPage;

import com.epam.qavn.core.AbstractTest;
import com.epam.qavn.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends AbstractTest {
    LoginPage loginPage = new LoginPage();

    @Test
    public void invalidLoginTest() {
        loginPage.clickLoginMenu(driver)
                .loginWithEmptyAccount(driver);
        Assert.assertEquals(loginPage.getEmailErrorMessage(driver), "Please enter a valid email address");
        Assert.assertEquals(loginPage.getPasswordErrorMessage(driver), "Please enter at least 8 characters");
    }

    @Test
    public void validLoginTest() {
        loginPage.clickLoginMenu(driver)
                .loginWithValidAccount(driver);
        Assert.assertEquals(loginPage.getSuccessMessage(driver), "You are logged in!");
    }
}
