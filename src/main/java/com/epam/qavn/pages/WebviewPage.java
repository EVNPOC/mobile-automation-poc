package com.epam.qavn.pages;

import com.epam.qavn.core.AbstractPage;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.time.Duration;

import static com.epam.qavn.constant.DefaultConfig.PAGE_LOAD_TIME;
import static com.epam.qavn.constant.DefaultConfig.SHORT_PRESS_TIME;

public class WebviewPage extends AbstractPage {

    private final AppiumDriver driver;

    private By menuLogin = AppiumBy.accessibilityId("Webview");
    private By btnGetStarted = AppiumBy.accessibilityId("Get Started");

    public WebviewPage(AppiumDriver driver) {
        this.driver = driver;
    }

    private WebElement findElementBy(By by) {
        return super.findElementBy(driver, by);
    }

    public WebviewPage tapWebviewMenu() {
        tapCenterOf(driver, findElementBy(menuLogin), Duration.ofMillis(PAGE_LOAD_TIME));
        return this;
    }

    public GettingStartedPage tapGettingStartedButton() {
        tapCenterOf(driver, findElementBy(btnGetStarted), Duration.ofMillis(SHORT_PRESS_TIME));
        return new GettingStartedPage(driver);
    }

}
