package com.pnt.utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * Created by Fuadkhan on 2/26/2017.
 */
public class BaseTest {
    //Read the system property value
    //Based on which,   i can create a driver instance(ios,android)
    String devicePlatform =System.getProperty("run");
    String testTarget =System.getProperty("target");
    AppiumDriver driver;
    DesiredCapabilities capabilities;
    AppiumDriverLocalService appiumLocal;
    //I want to get my run environment variable
    public AppiumDriver getDriver() throws Exception{
        if (testTarget.equalsIgnoreCase("sauceLabs")) {
            if (devicePlatform.equalsIgnoreCase("android")) {
                driver = new AndroidDriver(new URL("https://fuadkhan:7f56114c-06b2-46e4-bdd5-bfe48b1e5c7a@ondemand.saucelabs.com:443/wd/hub"), buildDesiredCapabilities());

            } else {
                driver = new IOSDriver(new URL("https://fuadkhan:7f56114c-06b2-46e4-bdd5-bfe48b1e5c7a@ondemand.saucelabs.com:443/wd/hub"), buildDesiredCapabilities());
            }

            }else {
            if (devicePlatform.equalsIgnoreCase("android")) {
                driver = new AndroidDriver(startAppiumServer(), buildDesiredCapabilities());


            } else {
                driver = new IOSDriver(startAppiumServer(), buildDesiredCapabilities());

            }

        }
//            if (devicePlatform.equalsIgnoreCase("android")) {
//                driver = new AndroidDriver(startAppiumServer(), buildDesiredCapabilities());
//
//
//            } else {
//                driver = new IOSDriver(startAppiumServer(), buildDesiredCapabilities());
//
//            }
            // driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
            return driver;
        }

    public DesiredCapabilities buildDesiredCapabilities(){
        capabilities = new DesiredCapabilities();
        capabilities.setCapability("newCommandTimout",60);
        if (testTarget.equalsIgnoreCase("sauceLabs")){
            if (devicePlatform.equalsIgnoreCase("android")){
                capabilities = new DesiredCapabilities();
                capabilities.setCapability("appiumVersion", "1.5.3");
                capabilities.setCapability("deviceName","Android Emulator");
                capabilities.setCapability("deviceOrientation", "portrait");
                capabilities.setCapability("browserName", "");
                capabilities.setCapability("platformVersion","5.1");
                capabilities.setCapability("platformName","Android");
                capabilities.setCapability("app","sauce-storage:sams.apk");
                capabilities.setCapability("appPackage","com.rfi.sams.android");
                capabilities.setCapability("appActivity","com.rfi.sams.android.main.SplashActivity");

            }else {
                capabilities.setCapability("automationName","XCUITest");
                capabilities.setCapability("platformName","iOS");
                capabilities.setCapability("platformVersion","6.0.1");
                capabilities.setCapability("deviceName","iPhone Simulator");
                capabilities.setCapability("browserName","");
                capabilities.setCapability("bundleId","com.samsclub.samsiphone.beta");
                capabilities.setCapability("app","path-SamsClub.app");

            }

        }else {
            if (devicePlatform.equalsIgnoreCase("android")){
                capabilities.setCapability("automationName","appium");
                capabilities.setCapability("platformName","Android");
                capabilities.setCapability("platformVersion","6.0.1");
                capabilities.setCapability("deviceName","S7 edge");
                capabilities.setCapability("browserName","");
                capabilities.setCapability("appPackage","com.rfi.sams.android");
                capabilities.setCapability("appActivity","com.rfi.sams.android.main.SplashActivity");

            }else {
                capabilities.setCapability("automationName","XCUITest");
                capabilities.setCapability("platformName","iOS");
                capabilities.setCapability("platformVersion","6.0.1");
                capabilities.setCapability("deviceName","iPhone Simulator");
                capabilities.setCapability("browserName","");
                capabilities.setCapability("bundleId","com.samsclub.samsiphone.beta");
                capabilities.setCapability("app","path-SamsClub.app");

            }

        }
        //if (devicePlatform.equalsIgnoreCase("android")){
            //capabilities.setCapability("automationName","appium");
            //capabilities.setCapability("platformName","Android");
           // capabilities.setCapability("platformVersion","6.0.1");
           // capabilities.setCapability("deviceName","S7 edge");
           // capabilities.setCapability("browserName","");
           // capabilities.setCapability("appPackage","com.rfi.sams.android");
           // capabilities.setCapability("appActivity","com.rfi.sams.android.main.SplashActivity");

        //}else {
           // capabilities.setCapability("automationName","XCUITest");
           // capabilities.setCapability("platformName","iOS");
            //capabilities.setCapability("platformVersion","6.0.1");
          //  capabilities.setCapability("deviceName","iPhone Simulator");
           // capabilities.setCapability("browserName","");
           // capabilities.setCapability("bundleId","com.samsclub.samsiphone.beta");
           // capabilities.setCapability("app","path-SamsClub.app");

        //}
        return capabilities;

    }
    public URL startAppiumServer(){
       appiumLocal= new AppiumServiceBuilder().usingAnyFreePort().build();
       appiumLocal.start();
       return appiumLocal.getUrl();
    }
    public void stopAppiiumServer() {
        if (testTarget.equalsIgnoreCase("local")) {
            appiumLocal.stop();
        }
    }

}
