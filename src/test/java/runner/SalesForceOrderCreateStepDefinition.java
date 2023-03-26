package runner;

import activity.Order.OrdersListScreen;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import task.*;

public class SalesForceOrderCreateStepDefinition {

    OrdersListScreen ordersList = new OrdersListScreen();
    Synchronization synchronization=new Synchronization();
    SearchCustomer searchCustomer=new SearchCustomer();
    AddOrder addOrder=new AddOrder();

    @Given("El vendedor sincroniza la data")
    public void elVendedorSincronizaLaData() {
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
        Assert.assertEquals(mensaje, ordersList.pedidoMensajeLabel.getText());
    }
}
