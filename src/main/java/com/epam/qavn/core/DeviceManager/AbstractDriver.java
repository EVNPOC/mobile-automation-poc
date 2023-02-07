package com.epam.qavn.core.DeviceManager;

import io.appium.java_client.AppiumDriver;

import java.net.MalformedURLException;

public interface AbstractDriver {
    AppiumDriver getDriver(Device device) throws MalformedURLException;
}
