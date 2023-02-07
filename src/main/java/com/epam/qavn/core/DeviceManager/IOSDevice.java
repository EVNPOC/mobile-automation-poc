package com.epam.qavn.core.DeviceManager;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class IOSDevice implements AbstractDriver {

    public AppiumDriver getDriver(Device device) {
        try {
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, device.getPlatformVersion());
            desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, device.getName());
            desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
            desiredCapabilities.setCapability(MobileCapabilityType.APP, "/path/to/ios/app.zip");
            URL url = new URL("http://127.0.0.1:4723/wd/hub");
            return new IOSDriver(url, desiredCapabilities);
        } catch (MalformedURLException urlException) {
            return null;
        }
    }
}
