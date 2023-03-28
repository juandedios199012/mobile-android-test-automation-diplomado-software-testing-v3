package task;

import activity.Order.OrderScreen;
import activity.Order.OrdersListScreen;
import activity.Product.ProductScreen;
import helper.JsonTestDataHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AddOrder {

    private static final Logger logger = LogManager.getLogger(JsonTestDataHelper.class);

    OrderScreen orderScreen = new OrderScreen();
    ProductScreen productScreen = new ProductScreen();
    OrdersListScreen ordersListScreen = new OrdersListScreen();
    GotoOrder gotoOrder = new GotoOrder();
    ShowMenuMobile showMenuMobile = new ShowMenuMobile();
    GoToPurchaseSummary goToPurchaseSummary = new GoToPurchaseSummary();
    SaveOrder saveOrder = new SaveOrder();

    public void withTheData(String BonificacionManual,String cantidadVenta) throws InterruptedException {
        showMenuMobile.selectOrderMenu();
        gotoOrder.goToOrderToCreate();

        logger.info("Click en el Boton +");
        orderScreen.agregarProductosButton.click();
        logger.info("Click en el Codigo de Producto");
        productScreen.codigoLabel.isControlDisplayed();
        productScreen.codigoLabel.click();

        logger.info("Click en Bonificacion Manual");
        if (!productScreen.bonificacionManualCheckBox.findControls2()){
            productScreen.bonificacionManualCheckBox.check();
        }

        logger.info("Ingresar Cantidad de Bonificacion manual");
        productScreen.stock1Label.isControlDisplayed();
        //productScreen.cantidad1TextBox.isVisibility();
        productScreen.cantidad1TextBox.setText(BonificacionManual);
        logger.info("Ingresar Cantidad de Productos de venta");
        productScreen.cantidad2TextBox.isVisibility();
        productScreen.cantidad2TextBox.setText(cantidadVenta);
        logger.info("Click en el boton Aceptar");
        productScreen.aceptarButton.click();
        goToPurchaseSummary.goToPurchaseSummary();
        saveOrder.onClickSaveOrder();

        Thread.sleep(10000);
        ordersListScreen.pedidoMensajeLabel.isControlDisplayed();
    }
}
