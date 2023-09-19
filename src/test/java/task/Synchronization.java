package task;

import activity.Synchronization.SynchronizationScreen;
import helper.JsonTestDataHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Synchronization {

    private static final Logger logger = LogManager.getLogger(JsonTestDataHelper.class);
    SynchronizationScreen synchronizationScreen = new SynchronizationScreen();

    public void synchronizaData() {
        logger.info("Click en Sincronizacion");
        synchronizationScreen.sincronizacionButton.click();
        logger.info("Click en el Menu");
        synchronizationScreen.menuButton.click();
        logger.info("Click en el boton Todo");
        synchronizationScreen.todoButton.isVisibility();
        synchronizationScreen.todoButton.click();
        logger.info("Click en el boton finalizar");
        synchronizationScreen.finalizarButton.isVisibility();
        synchronizationScreen.finalizarButton.click();
    }
}
