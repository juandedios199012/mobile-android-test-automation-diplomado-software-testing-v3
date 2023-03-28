package activity.Customer;

import appiumControl.Button;
import appiumControl.CheckBox;
import org.openqa.selenium.By;

public class CustomerListScreen {

    public Button clienteButton = new Button(By.id("com.uniflex.flexbusinessandroid:id/ibuClientes"));
    public CheckBox clienteCheckBox0 = new CheckBox(By.xpath("(//android.widget.CheckBox)[1]"));
    public CheckBox clienteCheckBox1 = new CheckBox(By.xpath("(//android.widget.CheckBox)[2]"));

}
