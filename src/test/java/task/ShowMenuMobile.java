package task;

import activity.menuMobile.MenuMobileScreen;
import helper.JsonTestDataHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ShowMenuMobile {

    private static final Logger logger = LogManager.getLogger(JsonTestDataHelper.class);

    MenuMobileScreen menuMobileScreen = new MenuMobileScreen();

    public void selectOrderMenu() {
        logger.info("click en el menu");
        menuMobileScreen.menuButton.click();
        menuMobileScreen.pedidoMenu.isVisibility();

        logger.info("click en el menu pedido");
        menuMobileScreen.pedidoMenu.click();
    }
    public void selectCollections() {

    }

    //
    public void selectCancelAnInvoice() {

    }
}
