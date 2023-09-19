package factoryDevices;

import com.github.javafaker.Faker;
import helper.DataProvider;
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
        DataProvider dataProvider = new DataProvider();
        DesiredCapabilities caps = new DesiredCapabilities();
        Faker faker = new Faker();
        String version = faker.app().version();

        // Set your access credentials
        caps.setCapability("browserstack.user", readBrowserStackFile.getUser());
        caps.setCapability("browserstack.key", readBrowserStackFile.getKey());


        // Set URL of the application under test
        caps.setCapability("app", "bs://0c303b0b8d85d9b11bcdb72cc29f0edd27d8e487");

        // Specify device and os_version for testing
        caps.setCapability("device", "Google Pixel 3");
        caps.setCapability("os_version", "9.0");

        //Specify Timezone
        caps.setCapability("browserstack.timezone", "Lima");

        // Set other BrowserStack capabilities
        caps.setCapability("project", "Flex Business Mobile");
        caps.setCapability("build", version);
        caps.setCapability("name", "Sales Flows");
        caps.setCapability("autoGrantPermissions", true);

        AndroidDriver driver;
        // Initialise the remote Webdriver using BrowserStack remote URL
        // and desired capabilities defined above
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
