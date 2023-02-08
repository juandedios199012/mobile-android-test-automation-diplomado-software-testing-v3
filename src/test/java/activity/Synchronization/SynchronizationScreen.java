package activity.Synchronization;

import appiumControl.Button;
import appiumControl.CheckBox;
import appiumControl.Label;
import org.openqa.selenium.By;

public class SynchronizationScreen {

    public CheckBox sincronizacionCheckBox = new CheckBox(By.id("com.uniflex.flexbusinessandroid:id/ibuSincronizacion"));
    public Button menuButton = new Button(By.xpath("//android.widget.ImageButton[@content-desc='FlexMobile']"));
    public Button todoButton = new Button(By.id("com.uniflex.flexbusinessandroid:id/tviTitulo")); //Text Todo x validar
    public Label tituloAlertaLabel = new Label(By.id("android:id/alertTitle")); // Sincronizando: Todo (3/3)
    public Label porcentajeProgresoLabel = new Label(By.id("android:id/progress_percent")); //text: 100 %
    public Label numeroProgresoLabel = new Label(By.id("android:id/progress_number")); //text :100/100
    public Button finalizarButton = new Button(By.id("android:id/button1"));
}
