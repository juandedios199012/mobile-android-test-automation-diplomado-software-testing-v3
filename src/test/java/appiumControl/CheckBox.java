package appiumControl;

import org.openqa.selenium.By;

public class CheckBox extends AppiumControl {

    public CheckBox(By locator) {
        super(locator);
    }

    public void check() {
        this.findControl();
        this.control.click();
    }
}
