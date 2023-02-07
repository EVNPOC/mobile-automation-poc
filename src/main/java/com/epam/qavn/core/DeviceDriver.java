package com.epam.qavn.core;

import com.epam.qavn.core.DeviceManager.*;
import com.epam.qavn.utils.JsonReader;
import com.google.gson.JsonObject;
import io.appium.java_client.AppiumDriver;

public class DeviceDriver {

    private final ThreadLocal<AppiumDriver> threadDriver;
    private String deviceSourcePath = "devices.json";
    private String unknownPlatformMessage = "Unknown Platform! please use ANDROID or IOS only";
    private PLATFORM platform;

    public DeviceDriver() {
        threadDriver = new ThreadLocal<>();
    }

    public void setDriver(String deviceName) throws UnknownPlatformException {
        JsonObject deviceInfo = JsonReader.getInstance().getFromResource(deviceSourcePath, deviceName);
        Device device = new Device(deviceInfo);
        try {
            platform = PLATFORM.valueOf(device.getPlatformName().toUpperCase());
        } catch (IllegalArgumentException exception) {
            throw new UnknownPlatformException(unknownPlatformMessage);
        }
        switch (platform) {
            case ANDROID:
                threadDriver.set(new AndroidDevice().getDriver(device));
                break;
            case IOS:
                threadDriver.set(new IOSDevice().getDriver(device));
                break;
            default:
                throw new UnknownPlatformException(unknownPlatformMessage);

        }
    }

    public AppiumDriver getDriver() {
        return threadDriver.get();
    }

    public void endDriver() {
        getDriver().quit();
        threadDriver.remove();
    }
}
