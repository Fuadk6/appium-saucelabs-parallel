package com.pnt.pages;


import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage {
    AppiumDriver driver;

    public  LandingPage(AppiumDriver driver){
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver),this);
    }

    @AndroidFindBy(id = "com.rfi.sams.android:id/sign_in_button")
    @iOSFindBy(accessibility = "Sign In")
    //if we dont get @iOSFindBy by typing press ctrl+hit enter; accessibility is equivalent t name property
    private MobileElement btnSignIn;

    @AndroidFindBy(uiAutomator = "new uiSelector().description(\"Sam's Club\")")
    //search for API documentation if you have confusion
    @iOSFindBy(accessibility = "logo-sams")
    private MobileElement btnMenu;

    public SignInPage clickSignInBtn(){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        btnSignIn.click();
        return new SignInPage(driver);
    }

    public MenuOptionsPage clickMenuBtn(){
        btnMenu.click();
        return new MenuOptionsPage(driver);
    }


}
