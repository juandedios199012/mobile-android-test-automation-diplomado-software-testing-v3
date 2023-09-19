package task;

import activity.Customer.CustomerInformationScreen;
import activity.Order.OrdersListScreen;
import activity.Order.PurchaseSummaryScreen;
import appiumControl.Direction;
import appiumControl.Swipe;
import helper.JsonTestDataHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GotoOrder {

    private static final Logger logger = LogManager.getLogger(JsonTestDataHelper.class);

    OrdersListScreen ordersListScreen = new OrdersListScreen();
    PurchaseSummaryScreen purchaseSummaryScreen = new PurchaseSummaryScreen();
    Swipe swipe = new Swipe();
    CustomerInformationScreen customerInformationScreen = new CustomerInformationScreen();

    public void goToOrderToModify() {
        ordersListScreen.pedidoMensajeLabel.isVisibility();
        ordersListScreen.pedidoMensajeLabel.click();
        ordersListScreen.modificarButton.isVisibility();
        ordersListScreen.modificarButton.click();
        purchaseSummaryScreen.grabarButton.isControlDisplayed();
        logger.info("Ir a Avance ");
        swipe.swipeScreen(Direction.RIGHT);
        logger.info("Ir a Pedido ");
        swipe.swipeScreen(Direction.RIGHT);
    }

    public void goToOrderToCreate() {
        logger.info("ver informacion de cliente");
        customerInformationScreen.menuInformacion.isVisibility();
        logger.info("Pantalla seleccionada de Informacion de cliente");
        customerInformationScreen.menuInformacion.isControlSelected();
        logger.info("ir a Planificion");
        swipe.swipeScreen(Direction.LEFT);
        logger.info("ir a Pedido");
        swipe.swipeScreen(Direction.LEFT);
    }
}
