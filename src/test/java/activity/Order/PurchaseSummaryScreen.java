package activity.Order;

import appiumControl.Button;
import appiumControl.ComboBox;
import appiumControl.Label;
import appiumControl.TextBox;
import org.openqa.selenium.By;

public class PurchaseSummaryScreen {

    public Button grabarButton = new Button(By.id("com.uniflex.flexbusinessandroid:id/actGrabar"));
    public Label cantidadIniciativasLabel = new Label(By.id("com.uniflex.flexbusinessandroid:id/tviIniciativasVendidas"));//(10.0) 20.0
    public Label cantidadImpulsadasLabel = new Label(By.id("com.uniflex.flexbusinessandroid:id/tviImpulsadasVendidas"));//(10.0) 20.0
    public Label direccionLabel = new Label(By.id("com.uniflex.flexbusinessandroid:id/tviDireccion"));// direccion: CLL. 4 MZ D LT. 37 ASOC. LAS VEGAS
    public ComboBox modalidadCreditoLabel = new ComboBox(By.id("com.uniflex.flexbusinessandroid:id/tviModalidadCredito"));//(10.0) 20.0
    public Label subTotalLabel = new Label(By.id("com.uniflex.flexbusinessandroid:id/tviSubTotal"));//20.0
    public Label descuentoLabel = new Label(By.id("com.uniflex.flexbusinessandroid:id/tviDescuento"));
    public Label recargoLabel = new Label(By.id("com.uniflex.flexbusinessandroid:id/tviRecargo"));//0.0
    public Label totalContadoLabel = new Label(By.id("com.uniflex.flexbusinessandroid:id/tviTotalContado"));//20.0
    public Label totalCreditoLabel = new Label(By.id("com.uniflex.flexbusinessandroid:id/tviTotalCredito"));//0.0
    public TextBox observacionTextBox = new TextBox(By.id("com.uniflex.flexbusinessandroid:id/tviObservacion"));//0.0
    public Label alertObservacionLabel = new Label(By.id("android:id/alertTitle"));//0.0
    public TextBox observacionEditText = new TextBox(By.id("android.widget.EditText"));//0.0

    public Button okButton = new Button(By.id("android:id/button1"));
    public Label totalLabel = new Label(By.id("com.uniflex.flexbusinessandroid:id/tviTotalComprobante"));//20.0
}
