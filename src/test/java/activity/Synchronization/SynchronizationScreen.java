package activity.Synchronization;

import appiumControl.Button;
import org.openqa.selenium.By;

public class SynchronizationScreen {

    public Button sincronizacionButton = new Button(By.id("com.uniflex.flexbusinessandroid:id/ibuSincronizacion"));
    public Button menuButton = new Button(By.xpath("//android.widget.ImageButton[@content-desc='FlexMobile']"));
    public Button todoButton = new Button(By.xpath("(//android.widget.TextView)[5]")); //Text Todo x validar
    public Button finalizarButton = new Button(By.id("android:id/button1"));
}
