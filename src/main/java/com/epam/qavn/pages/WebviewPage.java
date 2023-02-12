package com.epam.qavn.pages;

import com.epam.qavn.core.AbstractPage;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.time.Duration;

import static com.epam.qavn.constant.CONFIG.PAGE_LOAD_TIME;
import static com.epam.qavn.constant.CONFIG.SHORT_PRESS_TIME;

public class WebviewPage extends AbstractPage {

    private final AppiumDriver driver;

    private By menuWebView = AppiumBy.accessibilityId("Webview");
    private By btnGetStarted = AppiumBy.accessibilityId("Get Started");

    public WebviewPage(AppiumDriver driver) {
        this.driver = driver;
    }

    public WebviewPage tapWebviewMenu() {
        tapCenterOf(driver, findElementBy(driver, menuWebView), Duration.ofMillis(PAGE_LOAD_TIME));
        return this;
    }

    public GettingStartedPage tapGettingStartedButton() {
        tapCenterOf(driver, findElementBy(driver, btnGetStarted), Duration.ofMillis(SHORT_PRESS_TIME));
        return new GettingStartedPage(driver);
    }

}
