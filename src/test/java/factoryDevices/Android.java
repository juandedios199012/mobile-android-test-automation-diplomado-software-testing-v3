package factoryDevices;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class Android implements IDevice{
    @Override
    public AppiumDriver create() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName","d1ebea1c");
        capabilities.setCapability("platformVersion","7.1.1");
        capabilities.setCapability("appPackage","com.uniflex.flexbusinessandroid");
        capabilities.setCapability("appActivity","com.uniflex.flexbusinessandroid.activities.LoginActivity");
        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("autoGrantPermissions", true);
        AppiumDriver driver= null;
        try {
            driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        // implicit
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        return driver;
    }
}
