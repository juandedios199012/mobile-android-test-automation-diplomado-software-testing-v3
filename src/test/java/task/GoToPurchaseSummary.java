package task;

import activity.Order.Performance;
import appiumControl.Direction;
import appiumControl.Swipe;
import helper.JsonTestDataHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GoToPurchaseSummary {

    private static final Logger logger = LogManager.getLogger(JsonTestDataHelper.class);

    Performance performance = new Performance();
    Swipe swipe = new Swipe();

    public void goToPurchaseSummary() {
        logger.info("Pantalla de Pedido seleccionada");
        performance.menuPedido.isControlSelected();
        logger.info("ir a Avance");
        swipe.swipeScreen(Direction.LEFT);
        logger.info("ir a Confirmacion");
        swipe.swipeScreen(Direction.LEFT);
    }
}
