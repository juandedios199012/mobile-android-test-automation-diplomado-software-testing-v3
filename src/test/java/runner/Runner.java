package runner;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import singletonSession.Session;


public class Runner {

    final JavascriptExecutor jse = Session.getInstance().getDriver();

    @Before
    public void before() {
    }

    @After
    public void after(Scenario scenario) {
        if (scenario.isFailed()) {
            byte[] screen = Session.getInstance().getDriver().getScreenshotAs(OutputType.BYTES);
            scenario.attach(screen, "image/png", "App Failed Step");
            jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\":" +
                    " {\"status\":\"failed\", \"reason\": \"Save canceled order without success\"}}");

        } else {
            jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\":" +
                    " {\"status\": \"passed\", \"reason\": \"Save canceled order successfully!\"}}");
        }
        Session.getInstance().closeApp();
    }
}
