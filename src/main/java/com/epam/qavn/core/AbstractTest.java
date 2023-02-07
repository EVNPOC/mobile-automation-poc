package com.epam.qavn.core;

import com.epam.qavn.exception.UnknownPlatformException;
import io.appium.java_client.AppiumDriver;
import org.testng.annotations.*;

public abstract class AbstractTest {

    protected static AppiumDriver driver;
    private final DriverFactory driverFactory = new DriverFactory();

    @BeforeSuite
    public void startServerAndDevice() {
        System.out.println("----before suite----");
        System.out.println("this will start appium server and launch emulator");
    }

    @Parameters("device")
    @BeforeTest
    public void setUp(String deviceName) throws UnknownPlatformException {
        driverFactory.setDriver(deviceName);
        driver = driverFactory.getDriver();
    }

    @AfterTest
    public void tearDown() {
        driverFactory.removeDriver();
    }

    @AfterSuite
    public void shutdownServerAndDevice() {
        System.out.println("----after suite----");
        System.out.println("this will shutdown appium server and launch emulator");
    }
}
