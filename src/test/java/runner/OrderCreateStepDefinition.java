package runner;

import activity.Order.OrdersListScreen;
import io.cucumber.core.internal.com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import task.*;

public class OrderCreateStepDefinition {

    Synchronization synchronization = new Synchronization();
    SearchCustomer searchCustomer = new SearchCustomer();
    AddOrder addOrder = new AddOrder();
    GotoOrder gotoOrder = new GotoOrder();
    ModifyOrder modifyOrder = new ModifyOrder();
    GoToPurchaseSummary goToPurchaseSummary = new GoToPurchaseSummary();
    SaveOrder saveOrder = new SaveOrder();
    OrdersListScreen ordersListScreen = new OrdersListScreen();
    Login login = new Login();
    CancelOrder cancelOrder = new CancelOrder();

    @Given("El vendedor inicia sesion")
    public void elVendedorIniciaSesion() throws JsonProcessingException {
        login.withTheData();
    }

    @And("El vendedor sincroniza la data")
    public void elVendedorSincronizaLaData() throws JsonProcessingException {
        synchronization.synchronizaData();
    }

    @When("El vendedor selecciona al cliente {string} {string}")
    public void elVendedorSeleccionaAlCliente(String cliente, String tipo) {
        searchCustomer.selectCustomer(cliente, tipo);
    }

    @And("ingresa los productos y sus cantidades {string} {string}")
    public void ingresaLosProductosYSusCantidades(String cantidadBonificacion, String cantidadVenta ) throws InterruptedException {
        addOrder.withTheData(cantidadBonificacion,cantidadVenta);
    }

    @Then("graba el pedido con el mensaje {string}")
    public void grabaElPedidoConElMensaje(String mensaje) {
        Assert.assertEquals(mensaje, ordersListScreen.pedidoMensajeLabel.getText());
    }

    @And("El vendedor selecciona un pedido")
    public void elVendedorSeleccionaUnPedido() {
        gotoOrder.goToOrderToModify();
    }

    @When("El vendedor modifica el pedido")
    public void elVendedorModificaElPedido() throws InterruptedException {
        modifyOrder.withTheData();
        goToPurchaseSummary.goToPurchaseSummary();
        saveOrder.onClickSaveOrder();
    }

    @Then("graba la modificacion con el mensaje {string}")
    public void grabaLaModificacionConElMensaje(String mensaje) {
        Assert.assertEquals(mensaje, ordersListScreen.pedidoMensajeLabel.getText());
    }

    @And("y el estado de Operacion {string}")
    public void yElEstadoDeOperacion(String operacion) {
        Assert.assertEquals(operacion, ordersListScreen.operacionLabel.getText());
    }

    @And("El vendedor Anula el pedido")
    public void elVendedorAnulaElPedido() throws InterruptedException {
        cancelOrder.cancelOrderSales();
    }

    @Then("se actualiza el estado del Pedido a {string}")
    public void seActualizaElEstadoDePedidoAAnulado(String mensaje) {
        Assert.assertTrue(ordersListScreen.pedidoMensajeLabel.isControlDisplayed());
        Assert.assertEquals(mensaje, ordersListScreen.pedidoMensajeLabel.getText());
    }
    @And("y el estado de Operacion de la Anulacion {string}")
    public void yElEstadoDeOperacionDeLaAnulacion(String operacion) {
        Assert.assertEquals(operacion, ordersListScreen.operacionLabel.getText());
    }
}
