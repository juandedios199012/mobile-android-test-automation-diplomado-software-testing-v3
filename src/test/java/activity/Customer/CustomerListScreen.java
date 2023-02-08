package activity.Customer;

import appiumControl.Button;
import appiumControl.CheckBox;
import org.openqa.selenium.By;

public class CustomerListScreen {

    public Button clienteButton = new Button(By.id("com.uniflex.flexbusinessandroid:id/ibuClientes"));
    public CheckBox clienteCheckBox = new CheckBox(By.id("com.uniflex.flexbusinessandroid:id/cboSeleccionCliente")); //lista 2 clientes
    public Button menuButton = new Button(By.xpath("//android.widget.ImageButton[@content-desc='FlexMobile']"));//se repite
    public Button pedidoButton = new Button(By.id("com.uniflex.flexbusinessandroid:id/tviTitulo")); //text: Pedido


}
