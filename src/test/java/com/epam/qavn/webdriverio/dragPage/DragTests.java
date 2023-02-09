package com.epam.qavn.webdriverio.dragPage;

import com.epam.qavn.core.AbstractTest;
import com.epam.qavn.pages.DragPage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

import static com.epam.qavn.constant.DefaultConfig.SHORT_DRAG_DROP_TIME;

public class DragTests extends AbstractTest {
    DragPage dragPage = new DragPage();

    @Test
    public void dragTest() {
        dragPage.tapDragMenu(driver);
        WebElement dragElement = dragPage.getRandomDragElement(driver);
        WebElement dropElement = dragPage.getDestinationElement(driver, dragElement);
        dragPage.dragAndDrop(driver, dragElement, dropElement, Duration.ofMillis(SHORT_DRAG_DROP_TIME));
        Assert.assertFalse(dragElement.isDisplayed());
        Assert.assertFalse(dropElement.isDisplayed());
    }
}
