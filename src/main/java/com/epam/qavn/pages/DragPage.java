package com.epam.qavn.pages;

import com.epam.qavn.core.AbstractPage;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.Random;

import static com.epam.qavn.constant.CONFIG.SHORT_DRAG_DROP_TIME;
import static com.epam.qavn.constant.CONFIG.SHORT_PRESS_TIME;

public class DragPage extends AbstractPage {
    private AppiumDriver driver;
    private final By menuDrag = AppiumBy.accessibilityId("Drag");
    private final By lstDragItems = AppiumBy.xpath("//android.view.ViewGroup[contains(@content-desc,'drag')]");

    public DragPage(AppiumDriver driver) {
        this.driver = driver;
    }

    public DragPage tapDragMenu() {
        tapCenterOf(driver, findElementBy(driver, menuDrag), Duration.ofMillis(SHORT_PRESS_TIME));
        return this;
    }

    public WebElement getRandomDragElement() {
        Random random = new Random();
        return findElementsBy(driver, lstDragItems)
                .get(random.nextInt(findElementsBy(driver, lstDragItems).size() - 1));
    }

    public WebElement getDestinationElement(WebElement element) {
        String desLocator = getElementAttribute(element, "content-desc").replace("drag", "drop");
        return findElementBy(driver, AppiumBy.accessibilityId(desLocator));
    }

    public DragPage dragBetweenElements(WebElement start, WebElement end) {
        dragAndDrop(driver, start, end, Duration.ofMillis(SHORT_DRAG_DROP_TIME));
        return this;
    }
}