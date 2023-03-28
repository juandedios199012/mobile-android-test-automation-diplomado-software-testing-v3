package task;

import activity.Order.OrdersListScreen;
import activity.Order.PurchaseSummaryScreen;
import helper.JsonTestDataHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SaveOrder {

    private static final Logger logger = LogManager.getLogger(JsonTestDataHelper.class);
    PurchaseSummaryScreen purchaseSummaryScreen = new PurchaseSummaryScreen();
    OrdersListScreen ordersListScreen = new OrdersListScreen();

    public void onClickSaveOrder() throws InterruptedException {
        logger.info("Click en Grabar Pedido");
        purchaseSummaryScreen.grabarButton.click();
        Thread.sleep(6000);
        //ordersListScreen.pedidoMensajeLabel.isVisibility();
    }
}
