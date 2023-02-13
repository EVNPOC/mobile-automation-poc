package com.epam.qavn.webdriverio.webviewPage;

import com.epam.qavn.core.AbstractTest;
import com.epam.qavn.pages.WebviewPage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class WebviewTests extends AbstractTest {

    WebviewPage webviewPage;

    @BeforeClass
    public void initData() {
        webviewPage = new WebviewPage(driver);
    }

    @Test
    public void buttonGetStartedTest() {
        boolean isGettingStartedPageDisplayed = webviewPage
                .tapWebviewMenu()
                .scrollDownABit()
                .tapGettingStartedButton()
                .isGettingStartedPageDisplayed();
        Assert.assertTrue(isGettingStartedPageDisplayed, "Getting started page is not displayed");
    }
}
