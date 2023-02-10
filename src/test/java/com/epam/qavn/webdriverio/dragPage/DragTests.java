package com.epam.qavn.webdriverio.dragPage;

import com.epam.qavn.core.AbstractTest;
import com.epam.qavn.pages.DragPage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

import static com.epam.qavn.constant.CONFIG.SHORT_DRAG_DROP_TIME;

public class DragTests extends AbstractTest {
    DragPage dragPage;

    @BeforeClass
    public void initData() {
        dragPage = new DragPage(driver);
    }

    @Test
    public void dragTest() {
        dragPage.tapDragMenu();
        WebElement dragElement = dragPage.getRandomDragElement();
        WebElement dropElement = dragPage.getDestinationElement(dragElement);
        dragPage.dragAndDrop(driver, dragElement, dropElement, Duration.ofMillis(SHORT_DRAG_DROP_TIME));
        Assert.assertFalse(dragElement.isDisplayed());
        Assert.assertFalse(dropElement.isDisplayed());
    }
}
