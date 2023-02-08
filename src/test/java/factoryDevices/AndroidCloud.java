package factoryDevices;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class AndroidCloud implements IDevice {
    public AppiumDriver create() {
        DesiredCapabilities caps = new DesiredCapabilities();

        // Set your access credentials
        caps.setCapability("browserstack.user", "juanbs_j1aP5o");
        caps.setCapability("browserstack.key", "H6XypiWqx3eemvkYRgvq");


        // Set URL of the application under test
        caps.setCapability("app", "bs://c073ad07faa4331feb1365f308082ec83383975d");

        // Specify device and os_version for testing
        caps.setCapability("device", "Google Pixel 3");
        caps.setCapability("os_version", "9.0");

        //Specify Timezone
        caps.setCapability("browserstack.timezone", "Lima");

        // Set other BrowserStack capabilities
        caps.setCapability("project", "Diplomado Software Testing v3");
        caps.setCapability("build", "1.0");
        caps.setCapability("name", "DiplomadoApp-v3");
        caps.setCapability("autoGrantPermissions", true);

        AndroidDriver driver;
        // Initialise the remote Webdriver using BrowserStack remote URL
        // and desired capabilities defined above
        try {
            driver = new AndroidDriver(
                    new URL("http://hub.browserstack.com/wd/hub"), caps);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        return driver;
    }
}
