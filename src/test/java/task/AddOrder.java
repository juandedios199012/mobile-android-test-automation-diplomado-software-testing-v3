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
    GotoOrder gotoOrder = new GotoOrder();
    ShowMenuMobile showMenuMobile=new ShowMenuMobile();
    GoToPurchaseSummary goToPurchaseSummary = new GoToPurchaseSummary();
    SaveOrder saveOrder = new SaveOrder();

    public void withTheData(String cantidadVenta, String BonificacionManual) throws InterruptedException {
        showMenuMobile.selectOrderMenu();
        gotoOrder.goToOrderToCreate();

        logger.info("Click en el Boton +");
        orderScreen.agregarProductosButton.click();
        logger.info("Click en el Codigo de Producto");
        productScreen.codigoLabel.isControlDisplayed();
        productScreen.codigoLabel.click();
        logger.info("Click en Bonificacion Manual");
        productScreen.bonificacionManualCheckBox.check();
        logger.info("Ingresar Cantidad de Bonificacion manual");
        //productScreen.cantidad1TextBox.setText(order.getCantidadBonificacion());
        productScreen.cantidad1TextBox.setText(cantidadVenta);
        logger.info("Ingresar Cantidad de Productos de venta");
        //productScreen.cantidad2TextBox.setText(order.getCantidadVenta());
        productScreen.cantidad2TextBox.setText(BonificacionManual);
        logger.info("Click en el boton Aceptar");
        productScreen.aceptarButton.click();
        goToPurchaseSummary.goToPurchaseSummary();
        saveOrder.onClickSaveOrder();

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
