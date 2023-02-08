package com.epam.qavn.core.driverManager;

import com.epam.qavn.objects.DeviceInformation;
import io.appium.java_client.AppiumDriver;

public abstract class DriverManager {

    protected AppiumDriver driver;

    protected abstract void createDriver(DeviceInformation info);

    public AppiumDriver getDriver(DeviceInformation info) {
        if (driver == null) {
            createDriver(info);
        }
        return driver;
    }
}
