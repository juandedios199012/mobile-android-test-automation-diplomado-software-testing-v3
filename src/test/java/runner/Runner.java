package runner;

import helper.LocalReport;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.junit.Cucumber;
import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.OutputType;
import singletonSession.Session;

@RunWith(Cucumber.class)
public class Runner {

    @Before
    public void before() {

    }

    @After
    public void after(Scenario scenario) {
        if (scenario.isFailed()) {
            byte[] screen = Session.getInstance().getDriver().getScreenshotAs(OutputType.BYTES);
            scenario.attach(screen, "image/png", "TiendApp Failed Step");
        }
        Session.getInstance().closeApp();

        //comentar si la ejecucion se realiza por Jenkins
        //https://github.com/damianszczepanik/cucumber-reporting
        //LocalReport.generateReport();
    }
}
