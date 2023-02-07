package com.epam.qavn.webdriverio.loginPage;

import com.epam.qavn.core.AbstractTest;
import org.testng.annotations.Test;

public class LoginTests extends AbstractTest {

    @Test
    public void loginTest() {
        System.out.println("login test - session id: " + driver.getSessionId());
    }
}
