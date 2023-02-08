package com.epam.qavn.core.driverManager;

import com.epam.qavn.objects.DeviceInformation;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

public class AndroidDriverManager extends DriverManager {

    public AndroidDriverManager() {
        logger = LogManager.getLogger();
    }

    @Override
    public void createDriver(DeviceInformation device) {
        try {
            String appPath = Objects.requireNonNull(getClass().getClassLoader().getResource("NativeDemoApp-0.4.0.apk")).getPath();
            if (System.getProperty("os.name").toLowerCase().contains("windows")) {
                appPath = appPath.substring(1).replaceAll("/", "//");
            }
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, device.getPlatformName());
            desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, device.getPlatformVersion());
            desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, device.getName());
            desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
            desiredCapabilities.setCapability(MobileCapabilityType.APP, appPath);
            desiredCapabilities.setCapability("appWaitActivity", "*");
            URL url = new URL("http://127.0.0.1:4723/wd/hub");
            driver = new AndroidDriver(url, desiredCapabilities);
            logger.info("initiated Android driver for device " + device.getName());
        } catch (MalformedURLException urlException) {
            driver = null;
            logger.error(urlException.getMessage());
        }
    }
}
