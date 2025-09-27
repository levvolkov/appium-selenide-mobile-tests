package calculator.base;

import static io.qameta.allure.Allure.step;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

import calculator.pages.MobileObjects;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.qameta.allure.selenide.AllureSelenide;

public abstract class BaseTest {
    enum Platform {Android, IOS}
    BaseTest.Platform platform = BaseTest.Platform.Android;

    protected AppiumDriver driver;
    protected MobileObjects mobileObjects;

    private URL getUrl() {
        try {
            return new URL("http://127.0.0.1:4723");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @BeforeAll
    public static void loggingInSteps() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @BeforeEach
    public void setUp() {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("appium:ensureWebviewsHavePages", true);
        caps.setCapability("appium:nativeWebScreenshot", true);
        caps.setCapability("appium:newCommandTimeout", 3600);
        caps.setCapability("appium:connectHardwareKeyboard", true);

        if (platform == Platform.Android) {
            caps.setCapability("platformName", "Android");
            caps.setCapability("appium:deviceName", "Android Emulator");
            caps.setCapability("appium:appPackage", "com.google.android.calculator");
            caps.setCapability("appium:appActivity", "com.android.calculator2.Calculator");
            caps.setCapability("appium:automationName", "uiautomator2");

            driver = new AndroidDriver(getUrl(), caps);
        } else if (platform == Platform.IOS) {
            caps.setCapability("platformName", "iOS");
            caps.setCapability("appium:deviceName", "iPhone 11");
            caps.setCapability("appium:bundleId", "com.Shubhum-iosdev.Calculator-UI");
            caps.setCapability("appium:automationName", "XCUITest");

            driver = new IOSDriver(getUrl(), caps);
        }

        // Связываем Selenide с AppiumDriver
        WebDriverRunner.setWebDriver(driver);

        mobileObjects = new MobileObjects();
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) driver.quit();
    }
}
