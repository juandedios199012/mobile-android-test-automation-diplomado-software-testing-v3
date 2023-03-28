package activity.Order;

import appiumControl.Button;
import appiumControl.Label;
import org.openqa.selenium.By;

public class OrderScreen {

    public Button agregarProductosButton = new Button(By.id("com.uniflex.flexbusinessandroid:id/actAgregarItemPedido"));
    public Button eliminarButton = new Button(By.id("com.uniflex.flexbusinessandroid:id/btnEliminar"));
    public Label codigoProducto = new Label(By.xpath("//*[contains(@text,'010163')]"));//text 20.0
    public Label codigoProductoSalesForce = new Label(By.xpath("(//*[contains(@text,'290302')])[1]"));//text 20.0
}
