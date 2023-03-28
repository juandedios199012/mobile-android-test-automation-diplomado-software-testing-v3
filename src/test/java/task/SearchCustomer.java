package task;

import activity.Customer.CustomerListScreen;
import helper.JsonTestDataHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SearchCustomer {

    private static final Logger logger = LogManager.getLogger(JsonTestDataHelper.class);

    CustomerListScreen customerListScreen = new CustomerListScreen();

    public void selectCustomer(String cliente, String tipo) {
        logger.info("Click en Cliente");
        customerListScreen.clienteButton.click();

        switch (tipo) {
            case "Portafolio":
                logger.info("Click en el Cliente: " + cliente);
                customerListScreen.clienteCheckBox0.check();
                break;
            case "Fuerza de Venta":
                logger.info("Click en el Cliente: " + cliente);
                customerListScreen.clienteCheckBox1.check();
                break;
            default:
                System.out.println("No se encuentra un cliente");
                break;
        }
    }
}
