package com.epam.qavn.core.DeviceManager;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

public class AndroidDevice {

    public AppiumDriver getDriver(Device device) {
        try {
            String appPath = Objects.requireNonNull(getClass().getClassLoader().getResource("NativeDemoApp-0.4.0.apk")).getPath();
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, device.getPlatformName());
            desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, device.getPlatformVersion());
            desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, device.getName());
            desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
            desiredCapabilities.setCapability(MobileCapabilityType.APP, appPath);
            URL url = new URL("http://127.0.0.1:4723/wd/hub");
            return new AndroidDriver(url, desiredCapabilities);
        } catch (MalformedURLException urlException) {
            return null;
        }
    }
}
