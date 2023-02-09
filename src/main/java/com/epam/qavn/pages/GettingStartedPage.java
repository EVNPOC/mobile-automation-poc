package com.epam.qavn.pages;

import com.epam.qavn.core.AbstractPage;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class GettingStartedPage extends AbstractPage {

    private AppiumDriver driver;
    private By gettingStartedTitle = AppiumBy.xpath("//android.view.View[text()='Getting Started']");

    public GettingStartedPage(AppiumDriver driver) {
        this.driver = driver;
    }

    private WebElement findElementBy(By by) {
        return super.findElementBy(driver, by);
    }

    public boolean isGettingStartedPageDisplayed() {
        return findElementBy(gettingStartedTitle).isDisplayed();
    }

}
