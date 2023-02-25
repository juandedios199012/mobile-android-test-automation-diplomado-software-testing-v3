package task;

import activity.Order.OrderScreen;
import activity.Order.OrdersListScreen;
import activity.Order.Performance;
import activity.Order.PurchaseSummaryScreen;
import activity.Product.ProductScreen;
import appiumControl.Direction;
import appiumControl.Swipe;
import helper.JsonTestDataHelper;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;
import model.Order;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AddOrder {

    private static final Logger logger = LogManager.getLogger(JsonTestDataHelper.class);

    ObjectMapper objectMapper = new ObjectMapper();
    Order order = new Order();
    OrderScreen orderScreen = new OrderScreen();
    ProductScreen productScreen = new ProductScreen();
    Performance performance = new Performance();
    Swipe swipe = new Swipe();
    PurchaseSummaryScreen purchaseSummaryScreen = new PurchaseSummaryScreen();
    OrdersListScreen ordersListScreen = new OrdersListScreen();

    public void withTheData() throws InterruptedException {
        logger.info("Click en el Boton +");
        orderScreen.agregarProductosButton.click();
        logger.info("Click en el Codigo de Producto");
        productScreen.codigoLabel.isControlDisplayed();
        productScreen.codigoLabel.click();
        logger.info("Click en Bonificacion Manual");
        productScreen.bonificacionManualCheckBox.check();
        logger.info("Ingresar Cantidad de Bonificacion manual");
        //productScreen.cantidad1TextBox.setText(order.getCantidadBonificacion());
        productScreen.cantidad1TextBox.setText("1");
        logger.info("Ingresar Cantidad de Productos de venta");
        //productScreen.cantidad2TextBox.setText(order.getCantidadVenta());
        productScreen.cantidad2TextBox.setText("4");
        logger.info("Click en el boton Aceptar");
        productScreen.aceptarButton.click();
        logger.info("Pantalla de Pedido seleccionada");
        performance.menuPedido.isControlSelected();
        logger.info("ir a Avance");
        swipe.swipeScreen(Direction.LEFT);
        logger.info("ir a Confirmacion");
        swipe.swipeScreen(Direction.LEFT);
        logger.info("Click en Grabar Pedido");
        purchaseSummaryScreen.grabarButton.click();
        Thread.sleep(20000);
        ordersListScreen.pedidoMensajeLabel.isVisibility();
    }

    public void orderDataProvider() {
        try {
            order = objectMapper.readValue("resources/json/order.json", Order.class);
        } catch (Exception e) {
        }

    }
}
