package com.epam.qavn.pages;

import com.epam.qavn.core.AbstractPage;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.time.Duration;

import static com.epam.qavn.constant.DefaultConfig.ELEMENT_LOAD_TIME;
import static com.epam.qavn.constant.DefaultConfig.SHORT_PRESS_TIME;

public class FormPage extends AbstractPage {

    private AppiumDriver driver;
    private By menuForms = AppiumBy.accessibilityId("Forms");
    private By inputField = AppiumBy.accessibilityId("text-input");
    private By btnSwitch = AppiumBy.accessibilityId("switch");
    private By txtSwitch = AppiumBy.accessibilityId("switch-text");
    private By dropdown = AppiumBy.accessibilityId("Dropdown");
    private By dropdownPanel = AppiumBy.id("com.wdiodemoapp:id/select_dialog_listview");
    private By btnActive = AppiumBy.accessibilityId("button-Active");
    private By activeMessage = AppiumBy.accessibilityId("android:id/message");
    private By btnOK = AppiumBy.accessibilityId("android:id/button1");
    private String dropdownOptionXpath = "//android.widget.CheckedTextView[@text='%s']";

    public FormPage(AppiumDriver driver) {
        this.driver = driver;
    }

    public FormPage tapFormsMenu() {
        tapCenterOf(driver, findElementBy(driver, menuForms), Duration.ofMillis(SHORT_PRESS_TIME));
        return this;
    }

    public FormPage fillForm(String text, boolean enableSwitch, FormDropdown dropdown) {
        this.inputTextField(text)
                .switchToggle(enableSwitch)
                .selectDropdown(dropdown);
        return this;
    }

    public FormPage inputTextField(String text) {
        WebElement inputFieldElement = findElementBy(driver, inputField);
        waitUntilElementClickable(driver, inputFieldElement, Duration.ofMillis(ELEMENT_LOAD_TIME));
        inputText(driver, inputField, text);
        return this;
    }

    public FormPage switchToggle(boolean enable) {
        WebElement switchElement = findElementBy(driver, btnSwitch);
        waitUntilElementClickable(driver, switchElement, Duration.ofMillis(ELEMENT_LOAD_TIME));
        //when switch is ON, switch text is 'switch to turn OFF'
        boolean isSwitchOn = getElementText(driver, txtSwitch).contains("OFF");
        if (isSwitchOn != enable) {
            switchElement.click();
        }
        return this;
    }

    public FormPage selectDropdown(FormDropdown dropdownValue) {
        tapCenterOf(driver, driver.findElement(dropdown), Duration.ofMillis(SHORT_PRESS_TIME));
        waitUntilElementVisible(driver, dropdownPanel, Duration.ofMillis(ELEMENT_LOAD_TIME));

        WebElement dropdownOptionBy = findElementBy(driver, AppiumBy.xpath(
                String.format(dropdownOptionXpath, dropdownValue.getValue())));
        tapCenterOf(driver, dropdownOptionBy, Duration.ofMillis(SHORT_PRESS_TIME));

        return this;
    }

    public FormPage tapButtonActive() {
        WebElement activeButton = findElementBy(driver, btnActive);
        waitUntilElementClickable(driver, activeButton, Duration.ofMillis(ELEMENT_LOAD_TIME));
        tapCenterOf(driver, activeButton, Duration.ofMillis(SHORT_PRESS_TIME));
        return this;
    }

    public boolean isActiveMessageDisplayed() {
        return findElementBy(driver, activeMessage).isDisplayed();
    }

    public enum FormDropdown {
        WEB_DRIVER_IO("webdriver.io is awesome"),
        APPIUM("Appium is awesome"),
        THIS_APP("This app is awesome");
        private String value;

        FormDropdown(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

}
