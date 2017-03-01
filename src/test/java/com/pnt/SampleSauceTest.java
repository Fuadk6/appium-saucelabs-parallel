//package com.pnt;
//
//import io.appium.java_client.AppiumDriver;
//import io.appium.java_client.android.AndroidDriver;
//import io.appium.java_client.service.local.AppiumDriverLocalService;
//import io.appium.java_client.service.local.AppiumServiceBuilder;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import org.openqa.selenium.By;
//import org.openqa.selenium.remote.DesiredCapabilities;
//
//import java.net.URL;
//import java.util.concurrent.TimeUnit;
//
///**
// * Created by Fuadkhan on 2/27/2017.
// */
//public class SampleSauceTest {
//
//    DesiredCapabilities capabilities;
//    AppiumDriver driver;
//
//    @Before
//    public void setUp() throws Exception{
//        capabilities = new DesiredCapabilities();
//        capabilities.setCapability("appiumVersion", "1.5.3");
//        capabilities.setCapability("deviceName","Android Emulator");
//        capabilities.setCapability("deviceOrientation", "portrait");
//        capabilities.setCapability("browserName", "");
//        capabilities.setCapability("platformVersion","5.1");
//        capabilities.setCapability("platformName","Android");
//        capabilities.setCapability("app","sauce-storage:sams.apk");
//        capabilities.setCapability("appPackage","com.rfi.sams.android");
//        capabilities.setCapability("appActivity","com.rfi.sams.android.main.SplashActivity");
//        driver = new AndroidDriver(new URL("http://fuadkhan:7f56114c-06b2-46e4-bdd5-bfe48b1e5c7a@ondemand.saucelabs.com:443/wd/hub"),capabilities);
//        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
//
//    }
//    @Test
//    public void sampleTest(){
//        driver.findElement(By.id("com.rfi.sams.android:id/sign_in_button")).click();
//        driver.findElement(By.id("com.rfi.sams.android:id/sign_in_username")).sendKeys();
//        driver.findElement(By.id("com.rfi.sams.android:id/sign_in_password")).sendKeys();
//        driver.findElement(By.id("com.rfi.sams.android:id/sign_in_button")).click();
//    }
//
//    @After
//    public void tearDown() {
//        driver.quit();
//    }
//    //curl -u fuadkhan:7f56114c-06b2-46e4-bdd5-bfe48b1e5c7a -X POST -H "Content-Type: application/octet-stream" https://saucelabs.com/rest/v1/storage/fuadkhan/sams.apk?overwrite=true --data-binary @C:\Users\Fuadkhan\Desktop\Sam s Club Wholesale Savings_v5.8.4_apkpure.com.apk
//}
