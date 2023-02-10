package com.epam.qavn.webdriverio.formPage;

import com.epam.qavn.core.AbstractTest;
import com.epam.qavn.pages.FormPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SubmitFormTests extends AbstractTest {

    FormPage formPage = new FormPage(driver);

    @Test
    public void activeFormTest() {
        boolean messageIsDisplayed = formPage
                .fillForm("This is text", true, FormPage.FormDropdown.APPIUM)
                .tapButtonActive()
                .isActiveMessageDisplayed();
        Assert.assertTrue(messageIsDisplayed, "Active message is not displayed");
    }

}
