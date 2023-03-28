package activity.Customer;

import appiumControl.Button;
import appiumControl.CheckBox;
import org.openqa.selenium.By;

public class CustomerListScreen {

    public Button clienteButton = new Button(By.id("com.uniflex.flexbusinessandroid:id/ibuClientes"));
    //public CheckBox clienteCheckBox = new CheckBox(By.id("com.uniflex.flexbusinessandroid:id/cboSeleccionCliente")); //lista 2 clientes
    public CheckBox clienteCheckBox0 = new CheckBox(By.xpath("(//android.widget.CheckBox)[1]")); //lista 2 clientes
    public CheckBox clienteCheckBox1 = new CheckBox(By.xpath("(//android.widget.CheckBox)[2]"));
    //public List clienteCheckBox = new List(By.id("com.uniflex.flexbusinessandroid:id/cboSeleccionCliente")); //lista 2 clientes
}
