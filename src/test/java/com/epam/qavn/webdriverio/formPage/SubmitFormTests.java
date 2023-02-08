package com.epam.qavn.webdriverio.formPage;

import com.epam.qavn.core.AbstractTest;
import org.testng.annotations.Test;

public class SubmitFormTests extends AbstractTest {

    @Test
    public void submitForm() {
        System.out.println("submit form test - session id: " + driver.getSessionId());
    }
}
