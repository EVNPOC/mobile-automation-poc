package com.epam.qavn.core;

import com.epam.qavn.exception.UnknownPlatformException;
import com.epam.qavn.utils.AppiumServer;
import io.appium.java_client.AppiumDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.*;

public abstract class AbstractTest {

    protected Logger logger = LogManager.getLogger();
    protected static AppiumDriver driver;
    private final AppiumServer appiumRunner = new AppiumServer();
    private final DriverFactory driverFactory = new DriverFactory();

    @BeforeSuite
    public void startServerAndDevice() {
        logger.info("Before suite: start Appium server and launch Emulator");
        appiumRunner.start();
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
        logger.info("After suite: shutdown Appium server and close Emulator");
        appiumRunner.stop();
    }
}
