package task;

import activity.Customer.CustomerListScreen;
import helper.JsonTestDataHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GoToCustomerList {

    private static final Logger logger = LogManager.getLogger(JsonTestDataHelper.class);

    CustomerListScreen customerListScreen = new CustomerListScreen();

    public void () {
        customerListScreen.clienteButton.click();
        customerListScreen.clienteCheckBox.check();
    }
}
