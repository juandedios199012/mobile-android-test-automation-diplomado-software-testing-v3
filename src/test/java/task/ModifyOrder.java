package task;

import activity.Order.OrderScreen;
import activity.Product.ProductScreen;
import helper.JsonTestDataHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ModifyOrder {

    private static final Logger logger = LogManager.getLogger(JsonTestDataHelper.class);

    OrderScreen orderScreen = new OrderScreen();
    ProductScreen productScreen = new ProductScreen();

    public void withTheData() throws InterruptedException {

        String bolsas = "1";

        logger.info("Eliminar Producto Venta");
        if (!orderScreen.codigoProducto.findControls2()){
            orderScreen.codigoProducto.click();
            orderScreen.eliminarButton.click();
        }else{
            orderScreen.codigoProductoSalesForce.click();
            orderScreen.eliminarButton.click();
        }

        logger.info("Click en +");
        orderScreen.agregarProductosButton.click();

        logger.info("Agregar Combo");
        productScreen.combo.isControlDisplayed();
        productScreen.combo.click();

        logger.info("Cantidad de Combo Ingresada");
        productScreen.cantidad1TextBox.isControlDisplayed();
        productScreen.cantidad1TextBox.setText("1");

        logger.info("Click en el Boton Aceptar");
        productScreen.aceptarButton.click();

        logger.info("Agregar un segundo producto de venta");

        logger.info("Click en +");
        orderScreen.agregarProductosButton.isControlDisplayed();
        orderScreen.agregarProductosButton.click();

        logger.info("Agregar Prodcuto de venta");
        productScreen.codigoLabel2.isControlDisplayed();
        productScreen.codigoLabel2.click();

        logger.info("Cantidad de Unidades Ingresadas");
        productScreen.cantidad1TextBox.isControlDisplayed();
        productScreen.cantidad1TextBox.setText("6");

        logger.info("Cantidad de Bolsas Ingresadas {}", bolsas);
        productScreen.cantidad2TextBox.setText(bolsas);

        logger.info("Click en el Boton Aceptar");
        productScreen.aceptarButton.click();
        Thread.sleep(6000);
    }
}