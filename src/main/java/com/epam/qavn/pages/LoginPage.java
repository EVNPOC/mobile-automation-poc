package com.epam.qavn.pages;

import com.epam.qavn.core.AbstractPage;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.epam.qavn.constant.CONFIG.SHORT_PRESS_TIME;
import static com.epam.qavn.constant.CONFIG.SHORT_TIME_OUT;

public class LoginPage extends AbstractPage {
    private AppiumDriver driver;
    private static final String VALID_EMAIL = "abc@gmail.com";
    private static final String VALID_PASSWORD = "12345678";
    private final By menuLogin = AppiumBy.accessibilityId("Login");
    private final By txtEmail = AppiumBy.accessibilityId("input-email");
    private final By txtPassword = AppiumBy.accessibilityId("input-password");
    private final By btnLogin = AppiumBy.accessibilityId("button-LOGIN");
    private final By lblEmailErrorMessage = AppiumBy.xpath("//android.view.ViewGroup[./android.widget.EditText[@content-desc='input-email']]/following-sibling::android.widget.TextView[1]");
    private final By lblPasswordErrorMessage = AppiumBy.xpath("//android.view.ViewGroup[./android.widget.EditText[@content-desc='input-password']]/following-sibling::android.widget.TextView[1]");
    private final By lblSuccessMessage = AppiumBy.id("android:id/message");

    public LoginPage(AppiumDriver driver) {
        this.driver = driver;
    }

    public LoginPage login(String email, String password) {
        waitUntilElementVisible(driver, txtEmail, Duration.ofSeconds(SHORT_TIME_OUT));
        inputText(driver, txtEmail, email);
        inputText(driver, txtPassword, password);
        tapCenterOf(driver, findElementBy(driver, btnLogin), Duration.ofMillis(SHORT_PRESS_TIME));
        return this;
    }

    public LoginPage tapLoginMenu() {
        tapCenterOf(driver, findElementBy(driver, menuLogin), Duration.ofMillis(SHORT_PRESS_TIME));
        return this;
    }

    public LoginPage loginWithEmptyAccount() {
        login("", "");
        return this;
    }

    public LoginPage loginWithValidAccount() {
        login(VALID_EMAIL, VALID_PASSWORD);
        return this;
    }

    public String getEmailErrorMessage() {
        return getElementText(driver, lblEmailErrorMessage);
    }

    public String getPasswordErrorMessage() {
        return getElementText(driver, lblPasswordErrorMessage);
    }

    public String getSuccessMessage() {
        return getElementText(driver, lblSuccessMessage);
    }

}
