package task;

import activity.Order.PurchaseSummaryScreen;
import helper.JsonTestDataHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SaveOrder {

    private static final Logger logger = LogManager.getLogger(JsonTestDataHelper.class);
    PurchaseSummaryScreen purchaseSummaryScreen = new PurchaseSummaryScreen();

    public void onClickSaveOrder() {
        logger.info("Click en Grabar Pedido");
        purchaseSummaryScreen.grabarButton.click();
    }
}
