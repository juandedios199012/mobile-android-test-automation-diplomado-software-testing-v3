package factoryDevices;

import com.github.javafaker.Faker;
import helper.ReadBrowserStackFile;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class AndroidCloud implements IDevice {
    public AppiumDriver create() {
        ReadBrowserStackFile readBrowserStackFile = new ReadBrowserStackFile();
        DesiredCapabilities caps = new DesiredCapabilities();
        Faker faker = new Faker();
        String version = faker.app().version();

        // Set your access credentials
        caps.setCapability("browserstack.user", readBrowserStackFile.getUser());
        caps.setCapability("browserstack.key", readBrowserStackFile.getKey());

        // Set URL of the application under test
        caps.setCapability("app", "bs://6be940e51c88a63c600d91826a3bd03e20f86849");

        // Specify device and os_version for testing
        caps.setCapability("device", "Google Pixel 3");
        caps.setCapability("os_version", "9.0");
        caps.setCapability("browserstack.timezone", "Lima");

        // Set other BrowserStack capabilities
        caps.setCapability("project", "Flex Business Mobile");
        caps.setCapability("build", version);
        caps.setCapability("name", "Sales Flows");
        caps.setCapability("autoGrantPermissions", true);

        AndroidDriver driver;
        try {
            driver = new AndroidDriver(
                    new URL(readBrowserStackFile.getServer()), caps);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        return driver;
    }
}
