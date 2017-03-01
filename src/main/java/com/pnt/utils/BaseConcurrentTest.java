package com.pnt.utils;


import com.saucelabs.common.SauceOnDemandAuthentication;
import com.saucelabs.common.SauceOnDemandSessionIdProvider;
import com.saucelabs.junit.ConcurrentParameterized;
import com.saucelabs.junit.SauceOnDemandTestWatcher;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.*;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.LinkedList;

@RunWith(ConcurrentParameterized.class)

public class BaseConcurrentTest implements SauceOnDemandSessionIdProvider{
    public static String username = "fuadkhan";
    public static String accessKey = "7f56114c-06b2-46e4-bdd5-bfe48b1e5c7a";
    public static String seleniumURI;
    public static String buildTag;
    public static String app;
    /**
     * Constructs a {@link SauceOnDemandAuthentication} instance using the supplied user name/access
     * key.  To use the authentication supplied by environment variables or from an external file,
     * use the no-arg {@link SauceOnDemandAuthentication} constructor.
     */
    public SauceOnDemandAuthentication authentication = new SauceOnDemandAuthentication(username, accessKey);

    /**
     * JUnit Rule which will mark the Sauce Job as passed/failed when the test succeeds or fails.
     */
    @Rule
    public SauceOnDemandTestWatcher resultReportingTestWatcher = new SauceOnDemandTestWatcher(this, authentication);

    @Rule
    public TestName name = new TestName() {
        public String getMethodName() {
            return String.format("%s", super.getMethodName());
        }
    };

    protected String platformName;
    protected String appiumVersion;
    protected String platformVersion;
    protected String deviceName;
    protected String deviceOrientation;
    protected String sessionId;
    protected AppiumDriver driver;

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

    public BaseConcurrentTest(
            String platformName,
            String deviceName,
            String platformVersion,
            String appiumVersion,
            String deviceOrientation) {
        super();
        this.platformName = platformName;
        this.deviceName = deviceName;
        this.platformVersion = platformVersion;
        this.appiumVersion = appiumVersion;
        this.deviceOrientation = deviceOrientation;
    }

    /**
     * @return a LinkedList containing String arrays representing the browser combinations the test should be run against. The values
     * in the String array are used as part of the invocation of the test constructor
     */
    @ConcurrentParameterized.Parameters
    public static LinkedList browsersStrings() {
        LinkedList<String[]> browsers = new LinkedList<String[]>();
        browsers.add(new String[]{"Android","Android Emulator","5.1","1.5.3","portrait"});
        browsers.add(new String[]{"Android","Samsung Galaxy S4 Emulator","4.4","1.5.3","portrait"});

//        browsers.add(new String[]{"Android", "Android Emulator", "5.0", "1.5.3", "portrait"});
//        browsers.add(new String[]{"Android", "Samsung Galaxy S4 Emulator", "4.4", "1.5.3", "portrait"});
        //browsers.add(new String[]{"Android", "Samsung Galaxy S6 Device", "6.0", "1.5.3", "portrait"});

        return browsers;
    }

    /**
     * Constructs a new {@link org.openqa.selenium.remote.RemoteWebDriver} instance which is configured to use the capabilities
     * defined by the {@link #platformName}, {@link #platformVersion} and {@link #deviceName}
     * instance variables, and which is configured to run against ondemand.saucelabs.com, using
     * the username and access key populated by the {@link #authentication} instance.
     *
     * @throws Exception if an error occurs during the creation of the {@link org.openqa.selenium.remote.RemoteWebDriver}
     *                   instance.
     */
    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", this.platformName);
        capabilities.setCapability("platformVersion", this.platformVersion);
        capabilities.setCapability("deviceName", this.deviceName);
        capabilities.setCapability("deviceOrientation", this.deviceOrientation);
        capabilities.setCapability("appiumVersion", this.appiumVersion);
        capabilities.setCapability("app", app);


        String methodName = name.getMethodName();
        capabilities.setCapability("name", methodName);

        //Getting the build name.
        //Using the Jenkins ENV var. You can use your own. If it is not set test will run without
        // a build id.
        if (buildTag != null) {
            capabilities.setCapability("build", buildTag);
        }

        this.driver = new AndroidDriver(
                new URL("https://" + username + ":" + accessKey + seleniumURI + "/wd/hub"),
                capabilities);

        this.sessionId = driver.getSessionId().toString();
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }

    /**
     * @return the value of the Sauce Job id.
     */

    public String getSessionId() {
        return sessionId;
    }

    @BeforeClass
    public static void setupClass() {
        //get the uri to send the commands to.
        seleniumURI = "@ondemand.saucelabs.com:443";
        //If available add build tag. When running under Jenkins BUILD_TAG is automatically set.
        //You can set this manually on manual runs.
        buildTag = System.getenv("BUILD_TAG");

        app = "sauce-storage:sams.apk";
    }

}
