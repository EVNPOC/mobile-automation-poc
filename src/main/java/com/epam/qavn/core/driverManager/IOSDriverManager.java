package com.epam.qavn.core.driverManager;

import com.epam.qavn.objects.DeviceInformation;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class IOSDriverManager extends DriverManager {

    @Override
    public void createDriver(DeviceInformation device) {
        try {
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, device.getPlatformVersion());
            desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, device.getName());
            desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
            desiredCapabilities.setCapability(MobileCapabilityType.APP, "/path/to/ios/app.zip");
            URL url = new URL("http://127.0.0.1:4723/wd/hub");
            driver = new IOSDriver(url, desiredCapabilities);
        } catch (MalformedURLException urlException) {
            driver = null;
        }
    }
}
