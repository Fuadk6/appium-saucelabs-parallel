package com.pnt;

import com.pnt.pages.LandingPage;
import com.pnt.utils.BaseConcurrentTest;
import com.pnt.utils.BaseTest;
import io.appium.java_client.AppiumDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Fuadkhan on 2/26/2017.
 */
public class LoginTest extends BaseConcurrentTest {

    /**
     * Constructs a new instance of the test.  The constructor requires three string parameters,
     * which represent the operating system, version and browser to be used when launching a Sauce
     * VM.  The order of the parameters should be the same
     * as that of the elements within the {@link #browsersStrings()} method.
     *
     * @param platformName      name of the platformName. (Android, iOS, etc.)
     * @param deviceName        name of the device
     * @param platformVersion   Os version of the device
     * @param appiumVersion     appium version
     * @param deviceOrientation device orientation
     */
    public LoginTest(String platformName, String deviceName, String platformVersion, String appiumVersion, String deviceOrientation) {
        super(platformName, deviceName, platformVersion, appiumVersion, deviceOrientation);
    }


    @Test
    public void loginWithValidCredentials(){
        LandingPage landingPage = new LandingPage(driver);
        landingPage.clickSignInBtn().doValidLogin("hello@yahoo.com","1234fk");

    }
    @Test
    public void loginWithValidCredentials2(){
        LandingPage landingPage = new LandingPage(driver);
        landingPage.clickSignInBtn().doValidLogin("hello@yahoo.com","1234fk");

    }
    @Test
    public void loginWithValidCredentials3(){
        LandingPage landingPage = new LandingPage(driver);
        landingPage.clickSignInBtn().doValidLogin("hello@yahoo.com","1234fk");

    }

}
