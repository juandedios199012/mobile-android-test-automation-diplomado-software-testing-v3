package activity.Order;

import appiumControl.Button;
import appiumControl.Label;
import org.openqa.selenium.By;

public class OrdersListScreen {

    public Button modificarButton = new Button(By.id("com.uniflex.flexbusinessandroid:id/btnModificar")); //lista de pedidos
    public Button anularButton = new Button(By.id("com.uniflex.flexbusinessandroid:id/btnEliminar"));//lista de pedidos
    public Button confirmacionAnulacionButton = new Button(By.id("android:id/button1")); //poput
    public Label numeroLabel = new Label(By.id("com.uniflex.flexbusinessandroid:id/tviPedidoNumCp"));
    public Label totalPedidoLabel = new Label(By.id("com.uniflex.flexbusinessandroid:id/tviPedidoTotal"));
    public Label creditoLabel = new Label(By.id("com.uniflex.flexbusinessandroid:id/tviLineaCreditoPedido"));// Contado
    public Label operacionLabel = new Label(By.id("com.uniflex.flexbusinessandroid:id/tviPedidoOperacion"));// Contado
    public Label pedidoMensajeLabel = new Label(By.id("com.uniflex.flexbusinessandroid:id/tviPedidoMensaje"));// OK
    public Button agregarButton = new Button(By.id("com.uniflex.flexbusinessandroid:id/actAgregar")); //agregar otro pedido

}
