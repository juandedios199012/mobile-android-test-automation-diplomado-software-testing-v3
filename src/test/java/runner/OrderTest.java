package runner;

import activity.Customer.CustomerInformationScreen;
import activity.Customer.CustomerListScreen;
import activity.Order.OrderScreen;
import activity.Order.OrdersListScreen;
import activity.Order.Performance;
import activity.Order.PurchaseSummaryScreen;
import activity.Product.ProductScreen;
import activity.Synchronization.SynchronizationScreen;
import activity.login.LoginScreen;
import appiumControl.Direction;
import appiumControl.Swipe;
import helper.JsonTestDataHelper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;

public class OrderTest {

    private static final Logger logger = LogManager.getLogger(JsonTestDataHelper.class);

    LoginScreen loginScreen = new LoginScreen();
    SynchronizationScreen synchronizationScreen = new SynchronizationScreen();
    CustomerListScreen customerListScreen = new CustomerListScreen();
    CustomerInformationScreen customerInformationScreen = new CustomerInformationScreen();
    Swipe swipe = new Swipe();
    OrderScreen orderScreen = new OrderScreen();
    ProductScreen productScreen = new ProductScreen();
    PurchaseSummaryScreen purchaseSummaryScreen = new PurchaseSummaryScreen();
    Performance performance = new Performance();
    OrdersListScreen ordersList = new OrdersListScreen();
    //WaitUntilElement waitUntilElement;

    @Given("^el vendedor abre el aplicativo2$")
    public void elVendedorAbreElAplicativo() {
    }

    @When("ingresa sus credenciales {string} {string} {string}")
    public void ingresaSusCredenciales(String empresa, String usuario, String clave) {
        loginScreen.empresaTextBox.setText(empresa);
        loginScreen.usuarioTextBox.setText(usuario);
        loginScreen.passwordTextBox.setText(clave);
        loginScreen.ingresarButton.click();

    }

    @Then("graba el pedido")
    public void grabaElPedido() throws InterruptedException {
        synchronizationScreen.sincronizacionButton.click();
        synchronizationScreen.menuButton.click();
        synchronizationScreen.todoButton.isVisibility();
        synchronizationScreen.todoButton.click();
        synchronizationScreen.finalizarButton.isVisibility();
        synchronizationScreen.finalizarButton.click();
        customerListScreen.clienteButton.click();
        customerListScreen.clienteCheckBox.check();
        customerListScreen.menuButton.click();
        customerListScreen.pedidoMenu.isVisibility();
        logger.info("click en el menu pedidos");
        customerListScreen.pedidoMenu.click();
        logger.info("ver informacion de cliente");
        customerInformationScreen.menuInformacion.isVisibility();
        logger.info("Pantalla seleccionada de Informacion de cliente");
        customerInformationScreen.menuInformacion.isControlSelected();
        logger.info("ir a Planificion");
        swipe.swipeScreen(Direction.LEFT);
        logger.info("ir a Pedido");
        swipe.swipeScreen(Direction.LEFT);
        logger.info("Click en el Boton +");
        orderScreen.agregarProductosButton.click();
        logger.info("Click en el Codigo de Producto");
        productScreen.codigoLabel.isControlDisplayed();
        productScreen.codigoLabel.click();
        logger.info("Click en Bonificacion Manual");
        productScreen.bonificacionManualCheckBox.check();
        logger.info("Ingresar Cantidad de Bonificacion manual");
        productScreen.cantidad1TextBox.setText("1");
        logger.info("Ingresar Cantidad de Productos de venta");
        productScreen.cantidad2TextBox.setText("4");
        logger.info("Click en el boton Aceptar");
        productScreen.aceptarButton.click();
        logger.info("Pantalla de Pedido seleccionada");
        performance.menuPedido.isControlSelected();
        logger.info("ir a Avance");
        swipe.swipeScreen(Direction.LEFT);
        logger.info("ir a Confirmacion");
        swipe.swipeScreen(Direction.LEFT);
        /*logger.info("Click en Observación");
        purchaseSummaryScreen.observacionTextBox.isVisibility();
        purchaseSummaryScreen.observacionTextBox.click();
        logger.info("Ingresar Observación");
        purchaseSummaryScreen.alertObservacionLabel.isVisibility();
        purchaseSummaryScreen.observacionEditText.click();
        Session.getInstance().getDriver().hideKeyboard();
        purchaseSummaryScreen.observacionEditText.setText("Jhonsito");
        logger.info("Click en OK");
        purchaseSummaryScreen.okButton.click();*/
        logger.info("Click en Grabar Pedido");
        purchaseSummaryScreen.grabarButton.click();
        Thread.sleep(6000);
        ordersList.pedidoMensajeLabel.isVisibility();
        Assert.assertEquals("OK", ordersList.pedidoMensajeLabel.getText());
    }
}
