package task;

import activity.Synchronization.SynchronizationScreen;
import helper.JsonTestDataHelper;
import io.cucumber.core.internal.com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Synchronization {

    private static final Logger logger = LogManager.getLogger(JsonTestDataHelper.class);
    SynchronizationScreen synchronizationScreen = new SynchronizationScreen();


    public void synchronizaData() throws JsonProcessingException {
        synchronizationScreen.sincronizacionButton.click();
        synchronizationScreen.menuButton.click();
        synchronizationScreen.todoButton.isVisibility();
        synchronizationScreen.todoButton.click();
        synchronizationScreen.finalizarButton.isVisibility();
        synchronizationScreen.finalizarButton.click();
    }
}
