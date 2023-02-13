package com.epam.qavn.webdriverio.dragPage;

import com.epam.qavn.core.AbstractTest;
import com.epam.qavn.pages.DragPage;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class DragTests extends AbstractTest {
    DragPage dragPage;

    SoftAssert softAssert;

    @BeforeClass
    public void initData() {
        softAssert = new SoftAssert();
        dragPage = new DragPage(driver);
        dragPage.tapDragMenu();
    }

    @Test
    public void dragTest() {
        WebElement dragElement = dragPage.getRandomDragElement();
        WebElement dropElement = dragPage.getDestinationElement(dragElement);
        dragPage.dragBetweenElements(dragElement, dropElement);
        softAssert.assertFalse(dragElement.isDisplayed());
        softAssert.assertFalse(dropElement.isDisplayed());
        softAssert.assertAll();
    }
}
