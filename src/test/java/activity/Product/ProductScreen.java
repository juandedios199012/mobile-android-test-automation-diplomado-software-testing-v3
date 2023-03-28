package activity.Product;

import appiumControl.Button;
import appiumControl.CheckBox;
import appiumControl.Label;
import appiumControl.TextBox;
import org.openqa.selenium.By;

public class ProductScreen {

    public Label codigoLabel = new Label(By.id("com.uniflex.flexbusinessandroid:id/tviCodigo"));//lista de productos ,pantalla lista productos
    public Label stock1Label = new Label(By.id("com.uniflex.flexbusinessandroid:id/tviStock1"));
    public CheckBox bonificacionManualCheckBox = new CheckBox(By.id("com.uniflex.flexbusinessandroid:id/chkBonificacionManual1"));
    public TextBox cantidad1TextBox = new TextBox(By.id("com.uniflex.flexbusinessandroid:id/eteCantidad1"));
    public TextBox cantidad2TextBox = new TextBox(By.id("com.uniflex.flexbusinessandroid:id/eteCantidad2"));
    public Button aceptarButton = new Button(By.id("com.uniflex.flexbusinessandroid:id/btnAceptar"));
    public Label combo = new Label(By.xpath("//*[contains(@text,'COMBO')][1]"));
    public Label codigoLabel2 = new Label(By.xpath("//*[contains(@text,'290302')][1]"));
}
