package activity.Order;

import appiumControl.Button;
import appiumControl.CheckBox;
import appiumControl.Label;
import org.openqa.selenium.By;

public class OrderScreen {

    public Button agregarProductosButton = new Button(By.id("com.uniflex.flexbusinessandroid:id/actAgregarItemPedido"));
    public CheckBox seleccionCheckBox = new CheckBox(By.id("com.uniflex.flexbusinessandroid:id/cboCredito"));
    public Label totalItemLabel = new Label(By.id("com.uniflex.flexbusinessandroid:id/tviItemTotal"));//(10.0) 20.0
    public Button bonificaciomButton = new Button(By.id("com.uniflex.flexbusinessandroid:id/actBonificar"));
    public Button eliminarButton = new Button(By.id("com.uniflex.flexbusinessandroid:id/btnEliminar"));
    public Button modificarButton = new Button(By.id("com.uniflex.flexbusinessandroid:id/btnModificar"));
    public Label totalContadoLabel = new Label(By.id("com.uniflex.flexbusinessandroid:id/tviTotalContado"));//text 20.0
    public Label totalCreditoLabel = new Label(By.id("com.uniflex.flexbusinessandroid:id/tviTotalCredito"));//text 0.0
    public Label totalLabel = new Label(By.id("com.uniflex.flexbusinessandroid:id/tviTotalComprobante"));//text 20.0
}
