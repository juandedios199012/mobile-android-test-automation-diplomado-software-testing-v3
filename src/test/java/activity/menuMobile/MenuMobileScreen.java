package activity.menuMobile;

import appiumControl.Button;
import org.openqa.selenium.By;

public class MenuMobileScreen {

    public Button menuButton = new Button(By.xpath("//android.widget.ImageButton[@content-desc='FlexMobile']"));
    public Button pedidoMenu = new Button(By.xpath("//android.widget.TextView[@text='Pedido']"));
}
