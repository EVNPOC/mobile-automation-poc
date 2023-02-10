package com.epam.qavn.webdriverio.loginPage;

import com.epam.qavn.core.AbstractTest;
import com.epam.qavn.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LoginTests extends AbstractTest {
    LoginPage loginPage;

    @BeforeClass
    public void initData() {
        loginPage = new LoginPage(driver);
    }

    @Test
    public void invalidLoginTest() {
        loginPage.tapLoginMenu()
                .loginWithEmptyAccount();
        Assert.assertEquals(loginPage.getEmailErrorMessage(), "Please enter a valid email address");
        Assert.assertEquals(loginPage.getPasswordErrorMessage(), "Please enter at least 8 characters");
    }

    @Test
    public void validLoginTest() {
        loginPage.tapLoginMenu()
                .loginWithValidAccount();
        Assert.assertEquals(loginPage.getSuccessMessage(), "You are logged in!");
    }
}
