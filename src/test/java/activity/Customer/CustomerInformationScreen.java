package activity.Customer;

import appiumControl.ComboBox;
import appiumControl.Label;
import appiumControl.Scroll;
import org.openqa.selenium.By;

public class CustomerInformationScreen {

    public ComboBox direccionComboBox = new ComboBox(By.id("com.uniflex.flexbusinessandroid:id/tviDireccion"));
    public Label direccionLabel = new Label(By.id("android:id/select_dialog_listview"));//poput de lista de direcciones 2 minimo
    public Scroll menuInformacion = new Scroll(By.xpath("(//android.widget.TextView)[1]"));


}
