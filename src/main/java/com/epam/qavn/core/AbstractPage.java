package com.epam.qavn.core;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Collections;
import java.util.List;

import static com.epam.qavn.constant.DefaultConfig.SHORT_TIME_OUT;
import static java.time.Duration.ofMillis;
import static org.openqa.selenium.interactions.PointerInput.Kind.TOUCH;
import static org.openqa.selenium.interactions.PointerInput.MouseButton.LEFT;
import static org.openqa.selenium.interactions.PointerInput.Origin.viewport;

public class AbstractPage {
    private final PointerInput FINGER = new PointerInput(TOUCH, "finger");

    public WebElement findElementBy(AppiumDriver driver, By by) {
        waitUntilElementVisible(driver, by, Duration.ofSeconds(SHORT_TIME_OUT));
        return driver.findElement(by);
    }

    public List<WebElement> findElementsBy(AppiumDriver driver, By by) {
        waitUntilElementVisible(driver, by, Duration.ofSeconds(SHORT_TIME_OUT));
        return driver.findElements(by);
    }

    public String getElementText(AppiumDriver driver, By by) {
        return findElementBy(driver, by).getText();
    }

    public void inputText(AppiumDriver driver, By by, String text) {
        findElementBy(driver, by).sendKeys(text);
    }

    public void tapCenterOf(AppiumDriver driver, WebElement element, Duration duration) {
        Point point = getCenter(element);
        Sequence tap = new Sequence(FINGER, 1)
                .addAction(FINGER.createPointerMove(ofMillis(0), viewport(), point.getX(), point.getY()))
                .addAction(FINGER.createPointerDown(LEFT.asArg()))
                .addAction(new Pause(FINGER, duration))
                .addAction(FINGER.createPointerUp(LEFT.asArg()));
        driver.perform(Collections.singletonList(tap));
    }

    public void tapByPoint(AppiumDriver driver, Point point, Duration duration) {
        Sequence tap = new Sequence(FINGER, 1)
                .addAction(FINGER.createPointerMove(ofMillis(0), viewport(), point.getX(), point.getY()))
                .addAction(FINGER.createPointerDown(LEFT.asArg()))
                .addAction(new Pause(FINGER, duration))
                .addAction(FINGER.createPointerUp(LEFT.asArg()));
        driver.perform(Collections.singletonList(tap));
    }

    public void doSwipe(AppiumDriver driver, Point start, Point end, Duration duration) {
        Sequence swipe = new Sequence(FINGER, 1)
                .addAction(FINGER.createPointerMove(ofMillis(0), viewport(), start.getX(), start.getY()))
                .addAction(FINGER.createPointerDown(LEFT.asArg()))
                .addAction(FINGER.createPointerMove(duration, viewport(), end.getX(), end.getY()))
                .addAction(FINGER.createPointerUp(LEFT.asArg()));
        driver.perform(Collections.singletonList(swipe));
    }

    public void doSwipeHorizontalFromRight(AppiumDriver driver, WebElement element, Duration duration) {
        int centerY = element.getRect().y + (element.getSize().height / 2);
        double startX = element.getRect().x + (element.getSize().width * 0.9);
        double endX = element.getRect().x + (element.getSize().width * 0.1);
        doSwipe(driver, new Point((int) startX, centerY), new Point((int) endX, centerY), duration);
    }

    public void doSwipeHorizontalFromLeft(AppiumDriver driver, WebElement element, Duration duration) {
        int centerY = element.getRect().y + (element.getSize().height / 2);
        double startX = element.getRect().x + (element.getSize().width * 0.1);
        double endX = element.getRect().x + (element.getSize().width * 0.9);
        doSwipe(driver, new Point((int) startX, centerY), new Point((int) endX, centerY), duration);
    }

    public void doSwipeVerticalFromDown(AppiumDriver driver, WebElement element, Duration duration) {
        int centerX = element.getRect().x + (element.getSize().width / 2);
        double startY = element.getRect().y + (element.getSize().height * 0.9);
        double endY = element.getRect().y + (element.getSize().height * 0.1);
        doSwipe(driver, new Point(centerX, (int) startY), new Point(centerX, (int) endY), duration);
    }

    public void doSwipeVerticalFromUp(AppiumDriver driver, WebElement element, Duration duration) {
        int centerX = element.getRect().x + (element.getSize().width / 2);
        double startY = element.getRect().y + (element.getSize().height * 0.1);
        double endY = element.getRect().y + (element.getSize().height * 0.9);
        doSwipe(driver, new Point(centerX, (int) startY), new Point(centerX, (int) endY), duration);
    }

    public void dragAndDrop(AppiumDriver driver, WebElement start, WebElement end, Duration duration) {
        doSwipe(driver, getCenter(start), getCenter(end), duration);
    }

    public void dragAndDrop(AppiumDriver driver, Point start, Point end, Duration duration) {
        doSwipe(driver, start, end, duration);
    }

    public Point getCenter(WebElement element) {
        int centerY = element.getRect().y + (element.getSize().height / 2);
        int centerX = element.getRect().x + (element.getSize().width / 2);
        return new Point(centerX, centerY);
    }

    public void waitUntilElementVisible(AppiumDriver driver, WebElement element, Duration duration) {
        new WebDriverWait(driver, duration).until(ExpectedConditions.visibilityOf(element));
    }

    public void waitUntilElementVisible(AppiumDriver driver, By by, Duration duration) {
        new WebDriverWait(driver, duration).until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public void waitUntilElementClickable(AppiumDriver driver, WebElement element, Duration duration) {
        new WebDriverWait(driver, duration).until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitUntilElementClickable(AppiumDriver driver, AppiumBy by, Duration duration) {
        new WebDriverWait(driver, duration).until(ExpectedConditions.elementToBeClickable(by));
    }
}
