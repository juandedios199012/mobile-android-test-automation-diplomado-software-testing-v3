package task;

import activity.Customer.CustomerListScreen;
import helper.JsonTestDataHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SearchCustomer {

    private static final Logger logger = LogManager.getLogger(JsonTestDataHelper.class);

    CustomerListScreen customerListScreen = new CustomerListScreen();

    public void selectCustomer() {
        logger.info("Click en Cliente");
        customerListScreen.clienteButton.click();

        logger.info("Click en el Cliente");
        customerListScreen.clienteCheckBox.check();
    }
}
