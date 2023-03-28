package task;

import activity.Order.OrdersListScreen;
import helper.JsonTestDataHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CancelOrder {

    private static final Logger logger = LogManager.getLogger(JsonTestDataHelper.class);
    OrdersListScreen ordersListScreen = new OrdersListScreen();

    public void cancelOrderSales() throws InterruptedException {
        logger.info("Click en el Pedido");
        ordersListScreen.pedidoMensajeLabel.isVisibility();
        ordersListScreen.pedidoMensajeLabel.click();

        logger.info("Click en el Boton Anular");
        ordersListScreen.anularButton.isVisibility();
        ordersListScreen.anularButton.click();

        logger.info("Click en el Boton Confirmacion de Anulacion");
        ordersListScreen.confirmacionAnulacionButton.click();
        Thread.sleep(6000);
    }
}
