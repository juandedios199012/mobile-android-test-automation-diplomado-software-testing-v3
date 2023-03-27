package runner;

import activity.Order.OrdersListScreen;
import io.cucumber.core.internal.com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import task.*;

public class SalesForceOrderCreateStepDefinition {

    Synchronization synchronization=new Synchronization();
    SearchCustomer searchCustomer=new SearchCustomer();
    AddOrder addOrder=new AddOrder();
    GotoOrder gotoOrder = new GotoOrder();
    ModifyOrder modifyOrder = new ModifyOrder();
    GoToPurchaseSummary goToPurchaseSummary = new GoToPurchaseSummary();
    SaveOrder saveOrder = new SaveOrder();
    OrdersListScreen ordersListScreen = new OrdersListScreen();
    Login login =new Login();
    CancelOrder cancelOrder=new CancelOrder();

    @Given("El vendedor inicia sesion")
    public void elVendedorIniciaSesion() throws JsonProcessingException {
        login.withTheData();
    }

    @And("El vendedor sincroniza la data")
    public void elVendedorSincronizaLaData() throws JsonProcessingException {
        synchronization.synchronizaData();
    }

    @When("El vendedor selecciona al cliente")
    public void elVendedorSeleccionaAlCliente() {
        searchCustomer.selectCustomer();
    }

    @And("ingresa los productos y sus cantidades {string} {string}")
    public void ingresaLosProductosYSusCantidades(String cantidadVenta, String cantidadBonificacion) throws InterruptedException {
        addOrder.withTheData(cantidadVenta,cantidadBonificacion);
    }

    @Then("graba el pedido con el mensaje {string}")
    public void grabaElPedidoConElMensaje(String mensaje) {
        Assert.assertEquals(mensaje, ordersListScreen.pedidoMensajeLabel.getText());
    }

    @Given("El vendedor selecciona un pedido")
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

    @When("El vendedor Anula el pedido")
    public void elVendedorAnulaElPedido() {
        cancelOrder.cancelOrderSales();
    }
    @Then("se actualiza el estado del Pedido a Anulado")
    public void seActualizaElEstadoDePedidoAAnulado() {
        Assert.assertTrue(ordersListScreen.pedidoMensajeLabel.isControlDisplayed());
        Assert.assertEquals("ANULADO", ordersListScreen.pedidoMensajeLabel.getText());
        Assert.assertEquals("Anular", ordersListScreen.operacionLabel.getText());
    }
}
