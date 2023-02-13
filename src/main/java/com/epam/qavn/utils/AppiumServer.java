package com.epam.qavn.utils;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

import static io.appium.java_client.service.local.flags.GeneralServerFlag.DEBUG_LOG_SPACING;
import static io.appium.java_client.service.local.flags.GeneralServerFlag.LOG_LEVEL;


public class AppiumServer {

    private static AppiumDriverLocalService service;

    public void startAppiumServer() {
        final AppiumServiceBuilder builder = new AppiumServiceBuilder()
                .usingAnyFreePort()
                .withIPAddress("0.0.0.0")
                .usingPort(4723)
                .withArgument(LOG_LEVEL, "info:debug")
                .withArgument(DEBUG_LOG_SPACING)
                .withArgument(GeneralServerFlag.SESSION_OVERRIDE)
                // FYI: https://github.com/appium/java-client/blob/master/docs/v7-to-v8-migration-guide.md#appiumdriverlocalservice
                .withArgument(GeneralServerFlag.BASEPATH, "/wd/hub/");
        service = AppiumDriverLocalService.buildService(builder);
        service.start();
    }

    public void stopServer() {
        service.stop();
    }

}
