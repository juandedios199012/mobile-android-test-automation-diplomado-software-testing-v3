package appiumControl;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import singletonSession.Session;

import java.util.List;
import java.util.NoSuchElementException;

public class AppiumControl {
    protected By locator;
    protected WebElement control;
    protected List<WebElement> controls;

    public AppiumControl(By locator) {
        this.locator = locator;
    }


    public void findControl() {
        this.control = Session.getInstance().getDriver().findElement(this.locator);
    }

    public void findControls() {
        this.controls = Session.getInstance().getDriver().findElements(this.locator);
    }

    public Boolean findControls2() {
        boolean bonificacion = Session.getInstance().getDriver().findElements(this.locator).isEmpty();
        return bonificacion;
    }

    public void click() {
        this.findControl();
        this.control.click();
    }

    public String getText() {
        this.findControl();
        return this.control.getText();
    }

    public boolean isControlDisplayed() {
        try {
            this.findControl();
            return this.control.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isControlSelected() {
        try {
            this.findControl();
            return this.control.isSelected();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isPresent() {
        this.findControl();
        try {
            WebDriverWait wait = new WebDriverWait(Session.getInstance().getDriver(), 60);
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isInVisible() {
        this.findControl();
        try {
            WebDriverWait wait = new WebDriverWait(Session.getInstance().getDriver(), 60);
            wait.until(ExpectedConditions.invisibilityOf(Session.getInstance().getDriver().findElement(locator)));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isVisibility() {
        this.findControl();
        try {
            WebDriverWait wait = new WebDriverWait(Session.getInstance().getDriver(), 360);
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
