package com.epam.qavn.webdriverio.loginPage;

import com.epam.qavn.core.AbstractTest;
import com.epam.qavn.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class LoginTests extends AbstractTest {
    LoginPage loginPage;

    SoftAssert softAssert;

    @BeforeClass
    public void initData() {
        softAssert = new SoftAssert();
        loginPage = new LoginPage(driver);
        loginPage.tapLoginMenu();
    }

    @Test
    public void invalidLoginTest() {
        loginPage.loginWithEmptyAccount();
        softAssert.assertEquals(loginPage.getEmailErrorMessage(), "Please enter a valid email address");
        softAssert.assertEquals(loginPage.getPasswordErrorMessage(), "Please enter at least 8 characters");
    }

    @Test(groups = {"needClosePopup"})
    public void validLoginTest() {
        loginPage.loginWithValidAccount();
        Assert.assertEquals(loginPage.getSuccessMessage(), "You are logged in!");
    }

    @AfterMethod(onlyForGroups = "needClosePopup")
    public void closePopup() {
        loginPage.tapOkButton();
    }
}
